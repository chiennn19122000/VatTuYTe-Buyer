package com.example.myapplication.Home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Basket.Basket;
import com.example.myapplication.Basket.BasketAdapter;
import com.example.myapplication.R;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.myapplication.Constants.BaseUrlBuyer;


public class BasketFragment extends Fragment {

    ListView lvBasket;
    ArrayList<Basket> basketArrayList;
    BasketAdapter basketAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        lvBasket = (ListView) view.findViewById(R.id.lvProductList);

        addControls();
        addEvent();
        return view;
    }

    private void addControls() {

        basketArrayList = new ArrayList<>();
        /**
         * @param MainActivity.this
         * @param R.layout.item
         * @param bookList
         * */
        basketAdapter = new BasketAdapter(getActivity(),R.layout.item_custom, basketArrayList);
        lvBasket.setAdapter(basketAdapter);
    }

    private void addEvent() {
        createData();
    }

    /** Add data to bookList*/
    private void createData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data_login",MODE_PRIVATE);
        String username = preferences.getString("username", "");



        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<List<Basket>> call = apiInterface.getAllBasket( username);
        call.enqueue(new Callback<List<Basket>>() {
            @Override
            public void onResponse(Call<List<Basket>> call, Response<List<Basket>> response) {
                List<Basket> productsList = response.body();
                for (int i = 0; i<productsList.size() ; i++) {
                    basketArrayList.add(productsList.get(i));

                }
                basketAdapter.notifyDataSetChanged();
                Log.d(TAG, "onResponse" + "oke");
            }

            @Override
            public void onFailure(Call<List<Basket>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        basketAdapter.notifyDataSetChanged();


    }

}
