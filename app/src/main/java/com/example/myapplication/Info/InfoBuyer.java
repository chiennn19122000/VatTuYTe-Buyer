package com.example.myapplication.Info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoBuyer implements Serializable {
    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private String sdt;

    @SerializedName("address")
    private String address;


    @SerializedName("response")
    private String Response;

    public InfoBuyer(String username, String name, String sdt, String address) {
        this.username = username;
        this.name = name;
        this.sdt = sdt;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponse() {
        return Response;
    }

}
