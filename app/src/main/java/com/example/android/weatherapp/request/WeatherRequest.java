package com.example.android.weatherapp.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;



public class WeatherRequest extends JsonObjectRequest{
    // TODO: Change the URL
    private static String URL = "http://192.168.0.4:8080/cities/";
    private static int METHOD = Request.Method.GET;

    public WeatherRequest(String cityId) {
        super(METHOD, URL + cityId, null, new WeatherResponseListener(), new WeatherErrorListener());
    }

}
