package com.example.myapplication.Place;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaceSeller implements Serializable {
    @SerializedName("place")
    String place;

    public PlaceSeller(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
