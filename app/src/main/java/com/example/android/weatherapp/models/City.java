package com.example.android.weatherapp.models;

import android.support.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;


public class City implements SortedListAdapter.ViewModel{
    private final String name;
    private final int id;

    public City(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T t) {
        return this.equals(t);
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T t) {
        return this.id == ((City) t).id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
