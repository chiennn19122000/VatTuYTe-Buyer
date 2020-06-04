package com.example.myapplication.GetProduct;

import com.example.myapplication.Info.InfoBuyer;
import com.example.myapplication.Info.InfoSeller;
import com.example.myapplication.Product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getdata.php")
    Call<List<Product>> getAllProduct();

    @GET("getinfoseller.php")
    Call<List<InfoSeller>> getInfoSeller();

    @GET("getinfobuyer.php")
    Call<List<InfoBuyer>> getInfoBuyer();
}
