package com.example.android.weatherapp.request.weather;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;

import static com.example.android.weatherapp.request.RequestConfig.WEATHER_ENDPOINT;
import static com.example.android.weatherapp.request.RequestConfig.WEATHER_METHOD;


public class WeatherRequest extends JsonObjectRequest{

    public WeatherRequest(String serverAddr, String cityId, Context context) {
        super(WEATHER_METHOD, serverAddr + WEATHER_ENDPOINT + cityId, null, new WeatherResponseListener(context), new WeatherErrorListener(context));
    }

}
