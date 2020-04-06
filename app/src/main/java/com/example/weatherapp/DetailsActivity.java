package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weatherapp.model.City;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.weathericon)
    ImageView weathericon;
    @BindView(R.id.cityname)
    TextView cityname;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.temperature)
    TextView temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Gson gson = new Gson();
        String strTrack = getIntent().getStringExtra("City");
        City mCity = gson.fromJson(strTrack, City.class);

        cityname.setText(mCity.getName() + " , " + mCity.getApIsys().getCountry());
        weather.setText(mCity.getWeather().get(0).getMain());
        humidity.setText("" + mCity.getTemperature().getHumidity());
        temperature.setText("" + mCity.getTemperature().getTemp());

        String url = "http://openweathermap.org/img/w/" + mCity.getWeather().get(0).getIcon() + ".png";
        Glide.with(this).load(url)
                .into(weathericon);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
