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


public class Cities {

    public static ArrayList<City> cities;

    public static ArrayList<City> getCities() {
        if (cities == null) {
            // TODO: Add real logic
            cities = new ArrayList<>();
            for (int i = 0 ; i < 70000 ; i++ ) {
                String city = new String("Ciudad " + i);
                cities.add(new City(city));
            }
        }

        return cities;
    }

}
