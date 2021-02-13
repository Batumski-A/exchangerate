package com.example.exchangerate;

import android.media.Image;
import android.widget.ImageView;

public class exchange {
    private String currency,currencyFullName,price,distance;
    private String image;

    public exchange(String currency, String currencyFullName, String price, String distance, String image)
    {
        this.currency = currency;
        this.currencyFullName = currencyFullName;
        this.price = price;
        this.distance = distance;
        this.image  = image;
    }
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImageView(String image) {
        this.image = image;
    }
}
