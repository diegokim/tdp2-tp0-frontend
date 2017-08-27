package com.example.android.weatherapp.request.weather;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.android.weatherapp.models.CurrentCity;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherResponseListener implements Response.Listener<JSONObject> {
    private Context context;
    private final String NETWORK_ERROR_MESSAGE = "No​ ​fue​ ​posible​ ​conectarse​ ​al​ ​servidor,​ ​por favor​ ​reintente​ ​más​ ​tarde";


    WeatherResponseListener(Context context) {
        this.context = context;
    }

    public void onResponse(JSONObject response) {
        try {
            String temperature = response.getString("temperature");
            String pressure = response.getString("pressure");
            String weather = response.getString("weather");
            String time = response.getString("time");
            CurrentCity.getInstance().setTemperature(temperature);
            CurrentCity.getInstance().setPressure(pressure);
            CurrentCity.getInstance().setTime(time);
            CurrentCity.getInstance().setWeather(weather);
            CurrentCity.getInstance().commitChanges();
        } catch (JSONException exception) {
            // TODO: Refactor duplicated code
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, NETWORK_ERROR_MESSAGE, duration);
            toast.show();
            CurrentCity.getInstance().commitChanges();
        }

    }
}
