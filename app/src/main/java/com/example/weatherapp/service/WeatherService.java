package com.example.weatherapp.service;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.model.CityModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("/data/2.5/find?&units=metric")
    Call<CityModel> getCities(@Query("q") String key, @Query("APPID") String appid);
}
