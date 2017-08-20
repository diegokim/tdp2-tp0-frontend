package com.example.android.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.example.android.weatherapp.models.CurrentCity;
import com.example.android.weatherapp.request.WeatherRequest;
import com.example.android.weatherapp.request.WeatherRequestQueue;


import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {

    private static final int PICK_CITY = 1 ;
    private static final String CITY_NAME = "CITY_NAME" ;
    private TextView currentCity;
    private TextView bodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Set the text of the toolbar to be the current city
        // TODO: get the city from local storage
        currentCity = (TextView) findViewById(R.id.current_city);
        currentCity.setText(R.string.default_city);

        bodyText = (TextView) findViewById(R.id.body_text);

        CurrentCity.getInstance().addObserver(this);

        WeatherRequest weatherRequest = new WeatherRequest(null);
        WeatherRequestQueue.getInstance(this).addToRequestQueue(weatherRequest);
    }

    public void selectCity(View view) {
        Intent intent = new Intent(this, ListOfCitiesActivity.class);
        startActivityForResult(intent, PICK_CITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CITY) {
            if (resultCode == RESULT_OK) {
                //TODO: Ask for the weather and update
                String name = data.getStringExtra(CITY_NAME);
                currentCity.setText(name);
            }
        }
    }


    @Override
    public void update(Observable observable, Object o) {
        bodyText.setText(CurrentCity.getInstance().getName());
    }
}
