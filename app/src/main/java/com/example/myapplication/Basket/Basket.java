package com.example.myapplication.Basket;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Basket implements Serializable {
    @SerializedName("OrderId")
    private int OrderId;

    @SerializedName("ProductId")
    private int ProductId;

    @SerializedName("Username")
    private String Username;

    @SerializedName("Quantity")
    private int Quantity;

    @SerializedName("Pay")
    private int Pay;

    @SerializedName("Place")
    private String Place;

    @SerializedName("Id")
    private int IdGen;

    @SerializedName("NameProduct")
    private String NameProduct;

    @SerializedName("PriceProduct")
    private int PriceProduct;

    @SerializedName("InformationProduct")
    private String InformationProduct;

    @SerializedName("ImageProduct")
    private String ImageProduct;

    public Basket(int orderId, int productId, String username, int quantity, int pay, String place, int idGen, String nameProduct, int priceProduct, String informationProduct, String imageProduct) {
        OrderId = orderId;
        ProductId = productId;
        Username = username;
        Quantity = quantity;
        Pay = pay;
        Place = place;
        IdGen = idGen;
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        InformationProduct = informationProduct;
        ImageProduct = imageProduct;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPay() {
        return Pay;
    }

    public void setPay(int pay) {
        Pay = pay;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public int getIdGen() {
        return IdGen;
    }

    public void setIdGen(int idGen) {
        IdGen = idGen;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        PriceProduct = priceProduct;
    }

    public String getInformationProduct() {
        return InformationProduct;
    }

    public void setInformationProduct(String informationProduct) {
        InformationProduct = informationProduct;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
    }



    @Override
    public String toString() {
        return "Book{" +
                "title='" + NameProduct + '\'' +
                ", price='" + PriceProduct + '\'' +
                '}';
    }
}