package com.example.android.weatherapp.request.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.android.weatherapp.models.CurrentCity;


public class WeatherErrorListener implements Response.ErrorListener {
    Context context;
    private final String NETWORK_ERROR_MESSAGE = "No​ ​fue​ ​posible​ ​conectarse​ ​al​ ​servidor,​ ​por favor​ ​reintente​ ​más​ ​tarde";

    public WeatherErrorListener(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //TODO: Handle error
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, NETWORK_ERROR_MESSAGE, duration);
        toast.show();
        CurrentCity.getInstance().commitChanges();
    }
}
