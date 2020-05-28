package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class Register {


    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private int sdt;

    @SerializedName("address")
    private String address;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}

