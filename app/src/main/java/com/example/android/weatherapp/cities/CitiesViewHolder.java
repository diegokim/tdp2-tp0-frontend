package com.example.android.weatherapp.cities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.weatherapp.R;

import static android.app.Activity.RESULT_OK;


public class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cityName;
    private Activity activity;

    public CitiesViewHolder(View itemView, Activity activity) {
        super(itemView);
        this.activity = activity;

        // hago los find y cargo los atributos
        cityName = itemView.findViewById(R.id.city_name);
        cityName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent data = new Intent();
        data.putExtra("CITY_NAME",cityName.getText());
        activity.setResult(RESULT_OK, data);
        activity.finish();



    }
}
