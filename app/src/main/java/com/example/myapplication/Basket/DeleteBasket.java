package com.example.myapplication.Basket;

import com.google.gson.annotations.SerializedName;

public class DeleteBasket {

    @SerializedName("ProductId")
    private int product;

    @SerializedName("Username")
    private int username;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}

