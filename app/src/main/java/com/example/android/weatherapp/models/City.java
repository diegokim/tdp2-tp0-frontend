package com.example.android.weatherapp.models;

/**
 * Created by diegokim on 8/19/17.
 */

public class City {
    public String name;
    public String temperature;
    public String humidity;

    public City(String name, String humidity, String temperature) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
