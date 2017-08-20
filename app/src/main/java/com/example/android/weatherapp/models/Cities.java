package com.example.android.weatherapp.models;

import java.util.ArrayList;


public class Cities {
    public static ArrayList<String> getCities() {
        // TODO: Add real logic
        ArrayList<String> arr = new ArrayList<>();
        for ( int i =0 ; i < 100 ; i++){
            arr.add("Rosario, AR");
            arr.add("Buenos Aires, AR");
        }
        return arr;
    }
}
