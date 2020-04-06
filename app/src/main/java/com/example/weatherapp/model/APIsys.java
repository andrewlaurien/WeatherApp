package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class APIsys {
    @SerializedName("country")
    private String country;


    public String getCountry() {
        return country;
    }
}
