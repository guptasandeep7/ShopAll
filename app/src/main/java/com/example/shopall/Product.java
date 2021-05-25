package com.example.shopall;

import java.io.Serializable;

public class Product implements Serializable {

String id;
String name;
String photoUrl;
String description;
long rating;
long sellingPrice;
long mrp;
long quantity;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public long getMrp() {
        return mrp;
    }

    public void setMrp(long mrp) {
        this.mrp = mrp;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product(){}

    public Product(String id, String name, String photoUrl, String description, long rating, long sellingPrice, long mrp, long quantity) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
        this.rating = rating;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.quantity = quantity;
    }
}
