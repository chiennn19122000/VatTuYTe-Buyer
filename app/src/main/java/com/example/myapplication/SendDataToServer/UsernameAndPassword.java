package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

public class UsernameAndPassword {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
