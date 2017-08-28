package com.example.android.weatherapp.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class Cities extends Observable {

    private static Cities instance;

    private ArrayList<City> cities;

    private Cities () {
        cities = new ArrayList<>();
    }

    public static Cities getInstance() {
        if (instance == null) {
            instance = new Cities();
        }
        return instance;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> newCities) {
        if (newCities != null) {
            cities = newCities;
        } else {
            cities = new ArrayList<>();
        }
        commitChanges();
    }

    public void commitChanges(){
        System.gc();
        setChanged();
        notifyObservers();
    }


}
