package com.example.android.weatherapp.request;

import com.android.volley.Response;
import com.example.android.weatherapp.models.CurrentCity;

public class WeatherResponseListener<T> implements Response.Listener {
    public void onResponse(Object response) {
        CurrentCity.getInstance().setName(response.toString());
    }
}
