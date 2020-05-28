package com.example.myapplication.Product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("Id")
    private String id;
    @SerializedName("NameProduct")
    private String name;
    @SerializedName("PriceProduct")
    private String price;
    @SerializedName("InformationProduct")
    private String information;
    @SerializedName("ImageProduct")
    private String image;

    public Product(String id, String name, String price, String information, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.information = information;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
