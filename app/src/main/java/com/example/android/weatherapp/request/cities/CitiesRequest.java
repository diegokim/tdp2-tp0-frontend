package com.example.android.weatherapp.request.cities;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.example.android.weatherapp.request.RequestConfig.CITIES_ENDPOINT;
import static com.example.android.weatherapp.request.RequestConfig.CITIES_METHOD;


public class CitiesRequest extends JsonObjectRequest {

    public CitiesRequest(String serverAddr, JSONObject jsonRequest) {
        super(CITIES_METHOD, serverAddr + CITIES_ENDPOINT , jsonRequest, new CitiesResponseListener(), new CitiesErrorListener());
    }


}
