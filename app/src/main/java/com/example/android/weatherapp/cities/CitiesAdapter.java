package com.example.android.weatherapp.cities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.ItemCityBinding;
import com.example.android.weatherapp.models.City;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;
import java.util.List;


public class CitiesAdapter  extends SortedListAdapter<City> {

    public interface Listener {
        void onExampleModelClicked(City model);
    }

    private final Listener mListener;


    public CitiesAdapter(Context context, Comparator<City> comparator, Listener listener) {
        super(context, City.class, comparator);
        mListener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends City> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int i) {
        final ItemCityBinding binding = ItemCityBinding.inflate(inflater, parent, false);
        return new CitiesViewHolder(binding, mListener);
    }
}
