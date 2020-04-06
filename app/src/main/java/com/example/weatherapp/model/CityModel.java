package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityModel {
    @SerializedName("message")
    private String message;
    @SerializedName("cod")
    private String cod;
    @SerializedName("count")
    private int count;
    @SerializedName("list")
    private List<City> cities = null;

    public String getMessage() {
        return message;
    }

    public String getCod() {
        return cod;
    }

    public int getCount() {
        return count;
    }

    public List<City> getCities() {
        return cities;
    }
}
