package com.example.myapplication.Detail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.GetProduct.APIService;
import com.example.myapplication.Info.InfoSeller;
import com.example.myapplication.Product.Product;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Order;
import com.example.myapplication.SystemActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.Constants.BaseUrlBuyer;
import static com.example.myapplication.Constants.BaseUrlGet;
import static com.example.myapplication.Constants.BaseUrlUpload;
import static com.example.myapplication.Constants.SEND_DATA;

public class DetailProduct extends BaseActivity {

    @BindView(R.id.show_product)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id.plus)
    Button plus;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.spinner_place_seller)
    Spinner spinner;

    @BindView(R.id.add_basket)
    Button addbasket;
    @BindView(R.id.save_order)
    Button saveorder;


    Integer id1,soluong = 1,giatien;
    List<String> list;
    String place = "";
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_product;
    }

    @Override
    protected void setupListener() {
        Minus();
        Plus();
        AddBasket();
        Order();
    }

    @Override
    protected void populateData() {


        HideTitle();
        SetData();
    }

    private void SetData() {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(SEND_DATA);
        Picasso.with(DetailProduct.this).load(BaseUrlUpload+product.getImage()).into(image);

        name.setText(product.getName());
        price.setText(product.getPrice() + " VNĐ");
        info.setText(product.getInformation());
        id1 = Integer.valueOf(product.getId());

        giatien = Integer.parseInt(product.getPrice());
        money.setText("Thành tiền: " + giatien * soluong + " VNĐ");

        list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<InfoSeller>> call = apiService.getInfoSeller();
        call.enqueue(new Callback<List<InfoSeller>>() {
            @Override
            public void onResponse(Call<List<InfoSeller>> call, Response<List<InfoSeller>> response) {
                List<InfoSeller> placeSellerList = response.body();

                for (int i = 0; i<placeSellerList.size() ; i++) {
                    list.add(placeSellerList.get(i).getPlace());
                    Log.d(TAG, "onResponse" + placeSellerList.get(i).toString());
                }


            }

            @Override
            public void onFailure(Call<List<InfoSeller>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        list.add("Vị Trí");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = list.get(position);
                Log.d(TAG, "aa" + place);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void Minus()
    {
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong > 1)
                {
                    soluong--;
                    amount.setText(soluong + "");
                    money.setText("Thành tiền: " + giatien * soluong + " VNĐ");
                }
            }
        });
    }

    private void Plus()
    {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong++;
                amount.setText(soluong + "");
                money.setText("Thành tiền: " + giatien * soluong + " VNĐ");
            }
        });
    }

    private void Order()
    {
        saveorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place.equals("Vị Trí")||place.equals(""))
                {
                    Toast.makeText(DetailProduct.this,"Hãy chọn vị trí mua hàng",Toast.LENGTH_LONG).show();
                }
                else {
                    sendDataOrder();
                }
            }
        });
    }

    private void sendDataOrder()
    {
        int idproduct = id1;

        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        String username = preferences.getString("username", "");


        int quantity = soluong;
        int pay = soluong * giatien;

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<Order> call = apiInterface.order( idproduct,username,quantity,pay,place);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

                Order order = response.body();
                Log.d("Server Response",""+order.getResponse() + idproduct + username + quantity + pay);
                Toast.makeText(DetailProduct.this,"Đã đặt mua sản phẩm",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailProduct.this, SystemActivity.class));

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }

    private void AddBasket()
    {
        addbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataBasket();
            }
        });
    }

    private void sendDataBasket() {
        int idproduct = id1;

        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        String username = preferences.getString("username", "");


        int quantity = soluong;
        int pay = soluong * giatien;

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<Order> call = apiInterface.basket( idproduct,username,quantity,pay,place);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

                Order order = response.body();
                Log.d("Server Response",""+order.getResponse() + idproduct + username + quantity + pay);
                Toast.makeText(DetailProduct.this,"Đã thêm sản phẩm vào giỏ",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailProduct.this, SystemActivity.class));

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }
}
