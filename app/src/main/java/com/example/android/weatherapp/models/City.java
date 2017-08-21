package com.example.android.weatherapp.models;

/**
 * Created by diegokim on 8/19/17.
 */

public class City {
    public String name;
    public String temperature;
    public String humidity;
    public String weather;

    public City(String name, String humidity, String temperature, String weather) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = humidity;
        this.weather = weather;
    }

    public City(String name) {
        this.name = name;
        this.temperature = "S/D";
        this.humidity= "S/D";
        this.weather = "S/D";
    }
}
