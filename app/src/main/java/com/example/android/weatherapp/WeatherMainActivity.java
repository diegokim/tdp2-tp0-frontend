package com.example.android.weatherapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.weatherapp.models.CurrentCity;
import com.example.android.weatherapp.request.weather.WeatherRequest;
import com.example.android.weatherapp.request.NetworkRequestQueue;


import java.util.Observable;
import java.util.Observer;


public class WeatherMainActivity extends AppCompatActivity implements Observer {

    private static final String TEMPERATURE_UNIT = " °C";
    private static final String PRESSURE_UNIT = " Hpa";
    private static final String CITY_NAME_COUNTRY_SEPARATOR = ", ";
    private TextView currentName;
    private TextView temperature;
    private TextView pressure;
    private ProgressBar spinner;
    //TODO: Default api call?
    private static final String DEFAULT_ID = "5128638";
    private String serverAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeServerAddres();
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar) findViewById(R.id.main_progress_bar);

        // Set a custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Set the text of the toolbar to be the current city

        currentName = (TextView) findViewById(R.id.current_city);
        temperature = (TextView) findViewById(R.id.temperature);
        pressure = (TextView) findViewById(R.id.humidity);

        CurrentCity.getInstance().addObserver(this);
        // TODO: get the city from local storage
        fetchWeather();
    }

    private void fetchWeather() {
        activateSpinner();
        String id = CurrentCity.getInstance().getId();
        WeatherRequest weatherRequest = new WeatherRequest(serverAddr, id, this);
        NetworkRequestQueue.getInstance(this).addToRequestQueue(weatherRequest);
    }

    private void initializeServerAddres() {
        Intent data = getIntent();
        serverAddr = data.getStringExtra(IpAndPortSelectionActivity.SERVER_ADDR_EXTRA);
    }

    public void selectCity(View view) {
        Intent intent = new Intent(this, FirstLetterSelectionActivity.class);
        intent.putExtra(IpAndPortSelectionActivity.SERVER_ADDR_EXTRA, serverAddr);
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
                CurrentCity.getInstance().setId(Integer.toString(id));

                fetchWeather();
            }
        }
    }

    protected void activateSpinner() {
        temperature.setVisibility(View.GONE);
        pressure.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
    }

    protected void deactivateSpinner() {
        spinner.setVisibility(View.GONE);
        temperature.setVisibility(View.VISIBLE);
        pressure.setVisibility(View.VISIBLE);
    }

    private void changeBackground(String temperature, String humidity, String weather, String time) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main_activity_layout);
        int temp = Integer.parseInt(temperature );
        int timeInt = Integer.parseInt(time);
        // TODO: image background changes with:
        // layout.setBackgroundResource(R.drawable.{image-name});
        // the image must be placed in res/drawable
    }

    @Override
    public void update(Observable observable, Object o) {
        // Update the view when the currenct city change some of its atributes.
        String name = CurrentCity.getInstance().getName();
        String country = CurrentCity.getInstance().getCountry();
        String pressure = CurrentCity.getInstance().getHumidity(); // TODO: change to pressure
        String temperature = CurrentCity.getInstance().getTemperature();
        String weather = CurrentCity.getInstance().getWeather();
        String time = CurrentCity.getInstance().getTime();

        if (temperature != CurrentCity.NO_DEFAULT_DATA)
            changeBackground(temperature,pressure,weather,time);

        // Update the TextViews
        currentName.setText(name + CITY_NAME_COUNTRY_SEPARATOR + country);
        this.temperature.setText(temperature + TEMPERATURE_UNIT);
        this.pressure.setText(pressure + PRESSURE_UNIT);

        deactivateSpinner();
    }

    protected void refreshData(View view) {
        fetchWeather();
    }

}