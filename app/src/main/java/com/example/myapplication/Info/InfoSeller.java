package com.example.myapplication.Info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoSeller implements Serializable {

    @SerializedName("name")
    String name;
    @SerializedName("sdt")
    Integer sdt;
    @SerializedName("place")
    String place;

    public InfoSeller(String name, Integer sdt, String place) {
        this.name = name;
        this.sdt = sdt;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSdt() {
        return sdt;
    }

    public void setSdt(Integer sdt) {
        this.sdt = sdt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
