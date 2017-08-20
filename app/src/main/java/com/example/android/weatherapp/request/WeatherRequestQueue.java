package com.example.android.weatherapp.request;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/* Singleton to access the request queue */
public class WeatherRequestQueue {
    private static WeatherRequestQueue instance;
    private RequestQueue requestQueue;
    private static Context context;

    private WeatherRequestQueue(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized WeatherRequestQueue getInstance(Context context) {
        if ( instance == null ) {
            instance = new WeatherRequestQueue(context);
        }
        return instance;
    }

    /* Adding a request automatically sends it */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
