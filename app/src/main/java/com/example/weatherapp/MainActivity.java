package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.weatherapp.adapter.WeatherAdapter;
import com.example.weatherapp.model.City;
import com.example.weatherapp.viewmodel.WeatherViewModel;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    WeatherViewModel weatherViewModel;
    WeatherAdapter adapter;

    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.mProgressBar)
    ProgressBar mProgressBar;
    Gson gson = new Gson();

    WeatherAdapter.IClickListener iClickListener = position -> {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("City", gson.toJson(adapter.getItem(position)));
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new WeatherAdapter(this);
        adapter.setItemListener(iClickListener);

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.isStillSearching().observe(this, aBoolean -> {
            if (aBoolean) {
                mProgressBar.setVisibility(View.VISIBLE);
            } else {
                mProgressBar.setVisibility(View.GONE);
                adapter.setList(weatherViewModel.getCities().getValue());
            }
        });

        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 3) {
                    weatherViewModel.searchCity(newText);
                }
                return false;
            }
        });
        return true;
    }
}
