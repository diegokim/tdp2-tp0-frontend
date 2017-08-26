package com.example.android.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.weatherapp.models.CurrentCity;
import com.example.android.weatherapp.request.weather.WeatherRequest;
import com.example.android.weatherapp.request.WeatherRequestQueue;


import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {

    private static final String TEMPERATURE_UNIT = " °C";
    private static final String HUMIDITY_UNIT = " Hpa";
    private static final String CITY_NAME_COUNTRY_SEPARATOR = ", ";
    private TextView currentName;
    private TextView temperature;
    private TextView humidity;
    private ProgressBar spinner;
    //TODO: Default api call?
    private static final String DEFAULT_ID = "5128638";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar) findViewById(R.id.main_progress_bar);

        // Set a custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Set the text of the toolbar to be the current city

        currentName = (TextView) findViewById(R.id.current_city);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);

        CurrentCity.getInstance().addObserver(this);
        // TODO: get the city from local storage
        activateSpinner();
        WeatherRequest weatherRequest = new WeatherRequest(DEFAULT_ID);
        WeatherRequestQueue.getInstance(this).addToRequestQueue(weatherRequest);
    }

    public void selectCity(View view) {
        Intent intent = new Intent(this, FirstLetterSelectionActivity.class);
        startActivityForResult(intent, ListOfCitiesActivity.PICK_CITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ListOfCitiesActivity.PICK_CITY) {
            if (resultCode == RESULT_OK) {
                //TODO: Ask for the weather and update
                String name = data.getStringExtra(ListOfCitiesActivity.CITY_NAME_EXTRA);
                int id = data.getIntExtra(ListOfCitiesActivity.CITY_ID_EXTRA, -1);
                String country = data.getStringExtra(ListOfCitiesActivity.CITY_COUNTRY_EXTRA);
                Log.i("INFO ID OF CITY", Integer.toString(id));

                CurrentCity.getInstance().setName(name);
                CurrentCity.getInstance().setCountry(country);
                activateSpinner();
                WeatherRequest weatherRequest = new WeatherRequest(Integer.toString(id));
                WeatherRequestQueue.getInstance(this).addToRequestQueue(weatherRequest);
            }
        }
    }

    protected void activateSpinner() {
        temperature.setVisibility(View.GONE);
        humidity.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
    }

    protected void deactivateSpinner() {
        spinner.setVisibility(View.GONE);
        temperature.setVisibility(View.VISIBLE);
        humidity.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(Observable observable, Object o) {
        // Update the view when the currenct city change some of its atributes.
        String name = CurrentCity.getInstance().getName();
        String country = CurrentCity.getInstance().getCountry();
        String humidity = CurrentCity.getInstance().getHumidity();
        String temperature = CurrentCity.getInstance().getTemperature();
        String weather = CurrentCity.getInstance().getWeather();

        // Update the TextViews
        currentName.setText(name + CITY_NAME_COUNTRY_SEPARATOR + country);
        this.temperature.setText(temperature + TEMPERATURE_UNIT);
        this.humidity.setText(humidity + HUMIDITY_UNIT);
        deactivateSpinner();
    }
}