package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("ProductId")
    private int idproduct;

    @SerializedName("Username")
    private String username;

    @SerializedName("Quantity")
    private int quantity;

    @SerializedName("Pay")
    private int pay;

    @SerializedName("Place")
    private int Place;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
