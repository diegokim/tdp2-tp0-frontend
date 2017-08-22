package com.example.android.weatherapp.request;

import com.android.volley.Request;


public class RequestConfig {
    public static String URL = "http://192.168.0.4:8080";
    public static String WEATHER_ENDPOINT = "/cities/";
    public static String CITIES_ENDPOINT = "/cities";
    public static int WEATHER_METHOD = Request.Method.GET;
    public static int CITIES_METHOD = Request.Method.POST;
}
