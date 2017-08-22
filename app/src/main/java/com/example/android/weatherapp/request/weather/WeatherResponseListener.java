package com.example.android.weatherapp.request.weather;

import com.android.volley.Response;
import com.example.android.weatherapp.models.CurrentCity;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherResponseListener implements Response.Listener<JSONObject> {
    public void onResponse(JSONObject response) {
        // TODO: real logic
        try {
            String temperature = response.getString("temperature");
            String humidity = response.getString("humidity");
            String weather = response.getString("weather");
            CurrentCity.getInstance().setTemperature(temperature);
            CurrentCity.getInstance().setHumidity(humidity);
            CurrentCity.getInstance().setWeather(weather);
        } catch (JSONException exception) {
            // TODO: toast message
        }

    }
}
