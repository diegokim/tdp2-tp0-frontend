package com.example.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.android.weatherapp.cities.CitiesAdapter;
import com.example.android.weatherapp.models.Cities;
import com.example.android.weatherapp.models.City;

import java.util.ArrayList;


// Activity that displays the cities when we want to search
public class ListOfCitiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_cities);
        recyclerView = (RecyclerView) findViewById(R.id.list_of_cities);
        ArrayList<City> listOfCities = Cities.getCities();

        CitiesAdapter citiesAdapter = new CitiesAdapter(listOfCities, this);
        citiesAdapter.setHasStableIds(true);

        recyclerView.setAdapter(citiesAdapter);
        layoutManager = new LinearLayoutManager(this);


        // Trying to optimize
        layoutManager.setItemPrefetchEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
//
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

}
