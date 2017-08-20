package com.example.android.weatherapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_CITY = 1 ;
    private static final String CITY_NAME = "CITY_NAME" ;
    private Menu menu;
    private Toolbar toolbar;
    private TextView currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a custom toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Set the text of the toolbar to be the current city
        // TODO: get the city from local storage
        currentCity = (TextView) findViewById(R.id.current_city);
        currentCity.setText(R.string.default_city);
    }

    public void selectCity(View view) {
        Intent intent = new Intent(this, ListOfCitiesActivity.class);
        startActivityForResult(intent, PICK_CITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CITY) {
            if (resultCode == RESULT_OK) {
                //TODO: Ask for the weather and update
                currentCity.setText(data.getStringExtra(CITY_NAME));
            }
        }
    }

}
