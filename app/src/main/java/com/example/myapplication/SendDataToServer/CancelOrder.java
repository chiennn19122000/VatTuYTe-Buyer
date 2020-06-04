package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class CancelOrder {
    @SerializedName("orderid")
    private Integer order;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
