package com.example.android.weatherapp.request.cities;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.example.android.weatherapp.request.RequestConfig.CITIES_ENDPOINT;
import static com.example.android.weatherapp.request.RequestConfig.CITIES_METHOD;
import static com.example.android.weatherapp.request.RequestConfig.URL;


public class CitiesRequest extends JsonObjectRequest {

    public CitiesRequest(JSONObject jsonRequest) {
        super(CITIES_METHOD, URL + CITIES_ENDPOINT , jsonRequest, new CitiesResponseListener(), new CitiesErrorListener());
    }


}
