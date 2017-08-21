package com.example.android.weatherapp.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;


public class WeatherErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        //TODO: Handle error
        Log.e("ERR","response error weather");
    }
}
