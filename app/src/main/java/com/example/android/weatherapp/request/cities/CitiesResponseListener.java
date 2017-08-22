package com.example.android.weatherapp.request.cities;

import com.android.volley.Response;
import com.example.android.weatherapp.models.Cities;
import com.example.android.weatherapp.models.City;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class CitiesResponseListener implements Response.Listener<JSONObject> {

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<City> cities = new ArrayList<>();
            JSONArray array = response.getJSONArray("cities");
            for (int i = 0; i < array.length() ; i++) {
                JSONObject current = array.getJSONObject(i);
                City currentCity = new City(current.getString("name"),current.getInt("id"));
                cities.add(currentCity);
            }
            Cities.getInstance().setCities(cities);
        } catch (Exception e) {

        }

    }
}
