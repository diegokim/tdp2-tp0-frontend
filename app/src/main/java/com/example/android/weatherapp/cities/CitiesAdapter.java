package com.example.android.weatherapp.cities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.weatherapp.R;

import java.util.List;


public class CitiesAdapter  extends RecyclerView.Adapter<CitiesViewHolder> {

    private List<String> list;
    private Activity activity;

    public CitiesAdapter(List<String> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override // Crear la vista sin personalizar
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false);
        CitiesViewHolder vh = new CitiesViewHolder(v, activity);
        return vh;
    }

    @Override // Setear los datos
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        String name = list.get(position);
        holder.cityName.setText(name);
    }

    @Override // Obtener la cantidad de elementos
    public int getItemCount() {
        return list.size();
    }

//    @Override
//    public void onViewRecycled(CitiesViewHolder holder) {
//    }
}
