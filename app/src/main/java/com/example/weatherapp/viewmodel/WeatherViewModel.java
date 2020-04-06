package com.example.weatherapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.City;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<List<City>> mCities;
    private WeatherRepository weatherRepository;
    private MutableLiveData<Boolean> mIsSearch = new MutableLiveData<>();

    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
    }

    /**
     *Get the list of cities
     *
     * @return
     */
    public MutableLiveData<List<City>> getCities() {
        return mCities;
    }

    /**
     * searchCity functions sends a http
     * request to the openweather API
     *
     * @param key provided by the user
     * @return's a list of city
     */
    public MutableLiveData<List<City>> searchCity(String key) {
        mCities = weatherRepository.searchCities(key);
        return mCities;
    }

    /**
     * This functions is used to
     * check if the http request is
     * finish or not
     *
     * @return's boolean
     */
    public LiveData<Boolean> isStillSearching() {
        return weatherRepository.getIsStillSearching();
    }

}
