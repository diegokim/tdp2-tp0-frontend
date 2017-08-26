package com.example.android.weatherapp.cities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.weatherapp.databinding.ItemCityBinding;
import com.example.android.weatherapp.models.City;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class CitiesViewHolder extends SortedListAdapter.ViewHolder<City> {

    private final ItemCityBinding mBinding;

    public CitiesViewHolder(ItemCityBinding binding, CitiesAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);
        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull City city) {
        mBinding.setModel(city);
    }
}
