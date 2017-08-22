package com.example.android.weatherapp.models;


import java.util.Observable;

public class CurrentCity extends Observable {

    private static CurrentCity mInstance;
    private String name;
    private String humidity;
    private String temperature;
    private String weather;

    private CurrentCity (){
        this.name = "Nueva York";
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
            setChanged();
            notifyObservers();
        }
    }
    public void setTemperature (String newTemperature) {
        if (!newTemperature.equals(temperature)) {
            temperature = newTemperature;
            setChanged();
            notifyObservers();
        }
    }

    public void setHumidity (String newHumidity) {
        if (!newHumidity.equals(humidity) ) {
            humidity = newHumidity;
            setChanged();
            notifyObservers();
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
        if (!weather.equals(weather) ) {
            this.weather = weather;
            setChanged();
            notifyObservers();
        }
    }
}
