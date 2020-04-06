package com.example.weatherapp.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {
    private static String BaseURl = "http://api.openweathermap.org";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(httpLoggingInterceptor);


            retrofit = new Retrofit.Builder().baseUrl(BaseURl).client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }

}
