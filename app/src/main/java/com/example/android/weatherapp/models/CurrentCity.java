package com.example.android.weatherapp.models;


import java.util.Observable;

public class CurrentCity extends Observable {

    private static final String DEFAULT_NAME = "Nueva York";
    private static final String DEFAULT_ID = "5128638" ;
    private static final String DEFAULT_COUNTRY = "US" ;
    public static final String NO_DEFAULT_DATA = "S/D";
    private static CurrentCity mInstance;
    private String id;
    private String name;
    private String pressure;
    private String temperature;
    private String weather;
    private String country;
    private String time;

    private CurrentCity (){
        this.name = DEFAULT_NAME;
        this.id = DEFAULT_ID;
        this.country = DEFAULT_COUNTRY;
        this.pressure = NO_DEFAULT_DATA;
        this.temperature = NO_DEFAULT_DATA;
        this.weather = NO_DEFAULT_DATA;
        this.time = NO_DEFAULT_DATA;
    }

    public static CurrentCity getInstance() {
        if (mInstance == null) {
            mInstance = new CurrentCity();
        }
        return mInstance;
    }

    public void setName (String newName) {
            name = newName;
    }
    public void setTemperature (String newTemperature) {
        temperature = newTemperature;
    }

    public void setPressure(String newHumidity) {
            pressure = newHumidity;
    }
    public String getPressure() {
        return pressure;
    }
    public String getName () {
        return name;
    }

    public String getTemperature () {
        return temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void commitChanges() {
        setChanged();
        notifyObservers();
    }

    public String getCountry() {
        return country;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setTime(String time) { this.time = time; }

    public void setId(String id) {
        this.id = id;
    }
}
