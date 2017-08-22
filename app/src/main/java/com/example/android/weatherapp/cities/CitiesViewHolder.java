package com.example.android.weatherapp.cities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.ItemCityBinding;
import com.example.android.weatherapp.models.City;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import static android.app.Activity.RESULT_OK;


public class CitiesViewHolder extends SortedListAdapter.ViewHolder<City> {

    private final ItemCityBinding mBinding;

    public CitiesViewHolder(ItemCityBinding binding, CitiesAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

//    @Override
//    public void onClick(View view) {
//        Intent data = new Intent();
//        data.putExtra("CITY_NAME",cityName.getText());
//        activity.setResult(RESULT_OK, data);
//        activity.finish();
//    }

    @Override
    protected void performBind(@NonNull City city) {
        mBinding.setModel(city);
    }
}
