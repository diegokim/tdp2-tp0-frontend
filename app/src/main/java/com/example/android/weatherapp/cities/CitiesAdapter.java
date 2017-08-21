package com.example.android.weatherapp.cities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.weatherapp.R;
import com.example.android.weatherapp.models.City;

import java.util.List;


public class CitiesAdapter  extends RecyclerView.Adapter<CitiesViewHolder> {

    private List<City> list;
    private Activity activity;

    public CitiesAdapter(List<City> list, Activity activity) {
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
        String name = list.get(position).name;
        holder.cityName.setText(name);
        holder.setIsRecyclable(false);
    }

    @Override // Obtener la cantidad de elementos
    public int getItemCount() {
        return list.size();
    }

//    @Override
//    public void onViewRecycled(CitiesViewHolder holder) {
//    }
}
