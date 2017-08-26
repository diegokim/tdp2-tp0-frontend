package com.example.android.weatherapp.request.cities;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.android.weatherapp.models.CurrentCity;

/**
 * Created by diegokim on 8/25/17.
 */

public class CitiesErrorListener implements Response.ErrorListener {
    Context context;
    private final String NETWORK_ERROR_MESSAGE = "No​ ​fue​ ​posible​ ​conectarse​ ​al​ ​servidor,​ ​por favor​ ​reintente​ ​más​ ​tarde";

    public CitiesErrorListener(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //TODO: Handle error
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, NETWORK_ERROR_MESSAGE, duration);
        toast.show();
    }

}
