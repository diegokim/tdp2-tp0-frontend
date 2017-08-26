package com.example.android.weatherapp.request.weather;

import com.android.volley.toolbox.JsonObjectRequest;

import static com.example.android.weatherapp.request.RequestConfig.WEATHER_ENDPOINT;
import static com.example.android.weatherapp.request.RequestConfig.WEATHER_METHOD;


public class WeatherRequest extends JsonObjectRequest{

    public WeatherRequest(String serverAddr, String cityId) {
        super(WEATHER_METHOD, serverAddr + WEATHER_ENDPOINT + cityId, null, new WeatherResponseListener(), new WeatherErrorListener());
    }

}
