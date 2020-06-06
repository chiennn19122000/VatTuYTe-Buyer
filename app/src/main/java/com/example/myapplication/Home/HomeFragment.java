package com.example.myapplication.Home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Detail.DetailProduct;
import com.example.myapplication.GetProduct.APIService;
import com.example.myapplication.Product.Product;
import com.example.myapplication.Product.ProductAdapter;
import com.example.myapplication.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.Constants.BaseUrlGet;
import static com.example.myapplication.Constants.SEND_DATA;

public class HomeFragment extends Fragment {

    GridView gridView;
    ArrayList<Product> productList;
    ArrayList<Product> arrayList = new ArrayList<>();
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        gridView = (GridView) view.findViewById(R.id.screengridview);

        addControls();
        addEvent();
        selectItem();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    private void addControls() {

        productList = new ArrayList<>();
        /**
         * @param MainActivity.this
         * @param R.layout.item
         * @param bookList
         * */
        productAdapter = new ProductAdapter(getActivity(),R.layout.item_grid_view, productList);
        gridView.setAdapter(productAdapter);
    }

    private void addEvent() {
        createData();
    }

    /** Add data to bookList*/
    public void createData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Product>> call = apiService.getAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsList = response.body();
                for (int i = 0; i<productsList.size() ; i++) {
                    productList.add(productsList.get(i));
                    Log.d(TAG, "onResponse" + productsList.get(i).toString());
                }
                arrayList.addAll(productList);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        productAdapter.notifyDataSetChanged();
    }
    private void selectItem()
    {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = productList.get(position);

                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailProduct.class);
                intent.putExtra(SEND_DATA,product);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = stripAccents(s.toLowerCase(Locale.getDefault()));
                productList.clear();
                if (TextUtils.isEmpty(s)){
                    productList.addAll(arrayList);
                }
                else {
                    for (Product product : arrayList){
                        if (stripAccents(product.getName().toLowerCase(Locale.getDefault())).contains(text)){
                            productList.add(product);
                        }
                    }
                }
                productAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}