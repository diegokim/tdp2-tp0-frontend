package com.example.android.weatherapp.request.weather;

import com.android.volley.Response;
import com.example.android.weatherapp.models.CurrentCity;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherResponseListener implements Response.Listener<JSONObject> {
    public void onResponse(JSONObject response) {
        try {
            String temperature = response.getString("temperature");
            String humidity = response.getString("humidity");
            String weather = response.getString("weather");
            CurrentCity.getInstance().setProperties(weather,temperature,humidity);
        } catch (JSONException exception) {
            // TODO: toast message
        }

    }
}
