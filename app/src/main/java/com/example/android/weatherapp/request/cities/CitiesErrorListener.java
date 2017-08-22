package com.example.android.weatherapp.request.cities;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by diegokim on 8/22/17.
 */

public class CitiesErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("ERR", "Response error Cities");
    }
}
