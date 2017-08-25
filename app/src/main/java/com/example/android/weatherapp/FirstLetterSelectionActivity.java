package com.example.android.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.weatherapp.models.CurrentCity;

public class FirstLetterSelectionActivity extends AppCompatActivity {

    public static String LETTER_EXTRA = "LETTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_letter_selection);
        ListView listView = (ListView) findViewById( R.id.letter_list );
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.letter);

        // Add all the letters of the alphabet to the listview
        for (char c = 'A' ; c <= 'Z' ; c++) {
            adapter.add(Character.toString(c));
        }
        listView.setAdapter(adapter);
    }

    public void selectLetter(View view) {
        Intent intent = new Intent(this, ListOfCitiesActivity.class);
        intent.putExtra(LETTER_EXTRA, ((TextView)view).getText());
        String serverAddr = getIntent().getStringExtra(IpAndPortSelectionActivity.SERVER_ADDR_EXTRA);
        intent.putExtra(IpAndPortSelectionActivity.SERVER_ADDR_EXTRA, serverAddr);
        startActivityForResult(intent, ListOfCitiesActivity.PICK_CITY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ListOfCitiesActivity.PICK_CITY) {
            if (resultCode == RESULT_OK) {
                this.setResult(RESULT_OK, data);
                this.finish();
            }
        }
    }
}
