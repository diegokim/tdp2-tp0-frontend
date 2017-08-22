package com.example.android.weatherapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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
            setChanged();
            notifyObservers();
        }
    }



}
