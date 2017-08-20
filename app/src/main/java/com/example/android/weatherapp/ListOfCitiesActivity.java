package com.example.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.android.weatherapp.cities.CitiesAdapter;
import com.example.android.weatherapp.models.Cities;

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
        ArrayList<String> listOfCities = Cities.getCities();
        recyclerView.setAdapter(new CitiesAdapter(listOfCities, this));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(70);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }
}
