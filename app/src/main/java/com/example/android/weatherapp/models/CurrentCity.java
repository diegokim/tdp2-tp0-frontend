package com.example.android.weatherapp.models;


import java.util.Observable;

public class CurrentCity extends Observable {

    private static CurrentCity mInstance;
    private City mCity;

    private CurrentCity (){
        mCity = new City("Nueva York", "S/D", "S/D");
    }

    public static CurrentCity getInstance() {
        if (mInstance == null) {
            mInstance = new CurrentCity();
        }
        return mInstance;
    }

    public void setName (String newName) {
        if (!newName.equals(mCity.name)) {
            mCity.name = newName;
            setChanged();
            notifyObservers();
        }
    }
    public void setTemperature (String newTemperature) {
        if (!newTemperature.equals(mCity.temperature)) {
            mCity.temperature = newTemperature;
            setChanged();
            notifyObservers();
        }
    }

    public void setHumidity (String newHumidity) {
        if (!newHumidity.equals(mCity.humidity) ) {
            mCity.humidity = newHumidity;
            setChanged();
            notifyObservers();
        }
    }
    public String getHumidity () {
        return mCity.humidity;

    }
    public String getName () {
        return mCity.name;
    }
    public String getTemperature () {
        return mCity.temperature;
    }
}
