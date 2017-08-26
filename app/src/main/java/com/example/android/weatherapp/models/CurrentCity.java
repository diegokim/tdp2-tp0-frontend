package com.example.android.weatherapp.models;


import java.util.Observable;

public class CurrentCity extends Observable {

    private static CurrentCity mInstance;
    private String name;
    private String humidity;
    private String temperature;
    private String weather;
    private String country;

    private CurrentCity (){
        this.name = "Nueva York";
        this.country = "US";
        this.humidity = "S/D";
        this.temperature = "S/D";
        this.weather = "S/D";
    }

    public static CurrentCity getInstance() {
        if (mInstance == null) {
            mInstance = new CurrentCity();
        }
        return mInstance;
    }

    public void setName (String newName) {
        if (!newName.equals(name)) {
            name = newName;
            commitChanges();
        }
    }
    public void setTemperature (String newTemperature) {
        if (!newTemperature.equals(temperature)) {
            temperature = newTemperature;
            commitChanges();
        }
    }

    public void setHumidity (String newHumidity) {
        if (!newHumidity.equals(humidity) ) {
            humidity = newHumidity;
        }
    }
    public String getHumidity () {
        return humidity;

    }
    public String getName () {
        return name;
    }
    public String getTemperature () {
        return temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
        commitChanges();

    }

    public String getWeather() {
        return this.weather;
    }

    public void setCountry(String country) {
        this.country = country;
        commitChanges();
    }

    public void setProperties(String weather, String temperature, String humidity) {
        this.weather = weather;
        this.temperature = temperature;
        this.humidity = humidity;
        commitChanges();
    }

    public void commitChanges() {
        setChanged();
        notifyObservers();
    }

    public String getCountry() {
        return country;
    }
}
