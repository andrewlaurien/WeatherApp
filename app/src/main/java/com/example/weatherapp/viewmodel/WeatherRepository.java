package com.example.weatherapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.model.City;
import com.example.weatherapp.model.CityModel;
import com.example.weatherapp.service.WeatherService;
import com.example.weatherapp.service.WebClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherService webClient;
    private MutableLiveData<Boolean> mIsSearch = new MutableLiveData<>();

    public WeatherRepository() {
        webClient = WebClient.getClient().create(WeatherService.class);
    }

    public MutableLiveData<List<City>> searchCities(String key) {

        mIsSearch.setValue(true);

        final MutableLiveData<List<City>> data = new MutableLiveData<>();

        Call<CityModel> cityModelCall = webClient.getCities(key, BuildConfig.APPID);
        cityModelCall.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                data.postValue(response.body().getCities());
                mIsSearch.postValue(false);
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {

            }
        });

        return data;
    }

    public LiveData<Boolean> getIsStillSearching(){
        return mIsSearch;
    }

}
