package com.example.myapplication.Detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.Basket.Basket;
import com.example.myapplication.GetProduct.APIService;
import com.example.myapplication.Info.InfoSeller;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.CancelOrder;
import com.example.myapplication.SystemActivity;

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
import static com.example.myapplication.Constants.SEND_DATA;

public class DetailOrder extends BaseActivity {

    @BindView(R.id.ma_order)
    TextView maOrder;

    @BindView(R.id.ten_dai_ly)
    TextView tenDaiLy;

    @BindView(R.id.sdt)
    TextView sdt;

    @BindView(R.id.place)
    TextView place;

    @BindView(R.id.ma_product)
    TextView maProduct;

    @BindView(R.id.ten_product)
    TextView tenProduct;

    @BindView(R.id.don_gia)
    TextView donGia;

    @BindView(R.id.so_luong)
    TextView soLuong;

    @BindView(R.id.gia)
    TextView gia;

    @BindView(R.id.huy)
    Button huy;

    int Id;

    ArrayList<InfoSeller> infoSellerArrayList;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_order;
    }

    @Override
    protected void setupListener() {
        HuyOrder();
    }

    @Override
    protected void populateData() {
        callback();
        setTitle("Thông tin đơn hàng");
        SetData();
    }

    private void SetData() {
        Intent intent = getIntent();
        Basket basket = (Basket) intent.getSerializableExtra(SEND_DATA);

        Id = basket.getOrderId();
        maOrder.setText("- Mã đơn hàng: " + basket.getOrderId());


        place.setText("- Địa chỉ lấy hàng: " + basket.getPlace());
        maProduct.setText("- Mã sản phẩm: " + basket.getProductId());
        tenProduct.setText("- Tên sản phẩm: " + basket.getNameProduct());
        donGia.setText("- Đơn giá: " + basket.getPriceProduct());
        soLuong.setText("- Số lượng: " + basket.getQuantity());
        gia.setText("- Thành tiền: " + basket.getPay());
        getInfoSeller(basket.getPlace());
    }
    private void getInfoSeller(String place)
    {
        infoSellerArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<InfoSeller>> call = apiService.getInfoSeller();
        call.enqueue(new Callback<List<InfoSeller>>() {
            @Override
            public void onResponse(Call<List<InfoSeller>> call, Response<List<InfoSeller>> response) {
                List<InfoSeller> sellerList = response.body();
                for (int i = 0; i<sellerList.size() ; i++) {
                    if (sellerList.get(i).getPlace().equals(place))
                    {
                        infoSellerArrayList.add(sellerList.get(i));
                    }

                }
                tenDaiLy.setText("- Tên đại lý: " + infoSellerArrayList.get(0).getName());
                sdt.setText("- Số điện thoại: " + infoSellerArrayList.get(0).getSdt());
            }

            @Override
            public void onFailure(Call<List<InfoSeller>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void HuyOrder()
    {
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailOrder.this);
                builder.setTitle("Xóa đơn hàng");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Bạn có chắc chắn muốn hủy đơn hàng?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        xoaOrder(Id);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    private void xoaOrder(int idOrder)
    {
        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<CancelOrder> call = apiInterface.cancel( idOrder);

        call.enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {

                CancelOrder delete = response.body();
                Log.d("Server Response",""+delete.getResponse() );
                Toast.makeText(DetailOrder.this,"Đã hủy sản phẩm",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailOrder.this, SystemActivity.class));


            }

            @Override
            public void onFailure(Call<CancelOrder> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }
}
