package com.example.android.weatherapp.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;



public class WeatherRequest extends JsonObjectRequest{
    private static String URL = "http://ip.jsontest.com/";
    private static int METHOD = Request.Method.GET;

    public WeatherRequest(JSONObject jsonRequest) {
        super(METHOD, URL, jsonRequest, new WeatherResponseListener(), new WeatherErrorListener());
    }

    public static JSONObject parseCity() {
        return new JSONObject();
    }

}
