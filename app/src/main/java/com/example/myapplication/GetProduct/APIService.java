package com.example.myapplication.GetProduct;

import com.example.myapplication.Basket.Basket;
import com.example.myapplication.Place.PlaceSeller;
import com.example.myapplication.Product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface APIService {
    @GET("getdata.php")
    Call<List<Product>> getAllProduct();

    @GET("getplaceseller.php")
    Call<List<PlaceSeller>> getAllPlace();
//    @GET("orderdetails.php")
//    Call<List<Basket>> getAllBasket();
    /*@Field("username") String user*/
}
