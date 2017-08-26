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
    private static final String HUMIDITY_UNIT = " Hpa";
    private static final String CITY_NAME_COUNTRY_SEPARATOR = ", ";
    private TextView currentName;
    private TextView temperature;
    private TextView humidity;
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
        humidity = (TextView) findViewById(R.id.humidity);

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
        humidity.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
    }

    protected void deactivateSpinner() {
        spinner.setVisibility(View.GONE);
        temperature.setVisibility(View.VISIBLE);
        humidity.setVisibility(View.VISIBLE);
    }

    private void changeBackground(String temperature, String humidity, String weather, String time) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main_activity_layout);
        int temp = Integer.parseInt(temperature );
        int timeInt = Integer.parseInt(time);

        boolean temperature_hot = (temp > 20);
        boolean temperature_cold = (temp < 8);
        boolean temperature_normal = ((!temperature_hot) && (!temperature_cold));
        boolean day = (timeInt >= 9 && timeInt <= 18);
        boolean sunny = (weather.equals("clear") || weather.equals("sun"));
        boolean cloudy = (weather.equals("fog") || weather.equals("clouds") || weather.equals("mist"));
        boolean rainy = (weather.equals("rain") || weather.equals("drizzle"));

        Log.d("weather-time: ",time);
        Log.d("weather-desc: ",weather);
        Log.d("weather-temp: ", temperature);

        if (day && sunny && temperature_hot) layout.setBackgroundResource(R.drawable.beach_sun_day);
        if (day && sunny && temperature_normal) layout.setBackgroundResource(R.drawable.city_sun_day);
        if (day && sunny && temperature_cold) layout.setBackgroundResource(R.drawable.mountains_sun_day);
        if (day && cloudy && temperature_hot) layout.setBackgroundResource(R.drawable.jungle_cloud_day);
        if (day && cloudy && temperature_normal) layout.setBackgroundResource(R.drawable.city_cloud_day);
        if (day && cloudy && temperature_cold) layout.setBackgroundResource(R.drawable.bridge_cloud_day);
        if (day && rainy && temperature_hot) layout.setBackgroundResource(R.drawable.tropical_rain_day_3);
        if (day && rainy && temperature_normal) layout.setBackgroundResource(R.drawable.city_rain_day);
        if (day && rainy && temperature_cold) layout.setBackgroundResource(R.drawable.couple);
        if (!day && rainy) layout.setBackgroundResource(R.drawable.rain_window);
        if (!day && !rainy) layout.setBackgroundResource(R.drawable.stars);

        System.gc();
    }

    @Override
    public void update(Observable observable, Object o) {
        // Update the view when the currenct city change some of its atributes.
        String name = CurrentCity.getInstance().getName();
        String country = CurrentCity.getInstance().getCountry();
        String humidity = CurrentCity.getInstance().getHumidity();
        String temperature = CurrentCity.getInstance().getTemperature();
        String weather = CurrentCity.getInstance().getWeather();
        String time = CurrentCity.getInstance().getTime();

        if (temperature != CurrentCity.NO_DEFAULT_DATA)
            changeBackground(temperature,humidity,weather,time);

        // Update the TextViews
        currentName.setText(name + CITY_NAME_COUNTRY_SEPARATOR + country);
        this.temperature.setText(temperature + TEMPERATURE_UNIT);
        this.humidity.setText(humidity + HUMIDITY_UNIT);

        deactivateSpinner();
    }

    protected void refreshData(View view) {
        fetchWeather();
    }

}