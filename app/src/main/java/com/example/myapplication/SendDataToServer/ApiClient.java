package com.example.myapplication.SendDataToServer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.Constants.BaseUrlBuyer;


public class ApiClient {

    private static Retrofit retrofit;

    public static Retrofit getApiClient(String url) {

        retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
