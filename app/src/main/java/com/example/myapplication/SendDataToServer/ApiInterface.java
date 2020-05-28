package com.example.myapplication.SendDataToServer;

import com.example.myapplication.Basket.Basket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> regis( @Field("username") String user, @Field("password") String pass,@Field("name") String name, @Field("sdt") Integer sdt, @Field("address") String address);

    @FormUrlEncoded
    @POST("login.php")
    Call<UsernameAndPassword> loginacc( @Field("username") String user, @Field("password") String pass);


    @FormUrlEncoded
    @POST("order.php")
    Call<Order> order( @Field("ProductId") Integer idproduct, @Field("Username") String user,@Field("Quantity") Integer quantity, @Field("Pay") Integer pay,@Field("Place") String place);

    @FormUrlEncoded
    @POST("orderdetails.php")
    Call<List<Basket>> getAllBasket(@Field("Username") String user);
}
