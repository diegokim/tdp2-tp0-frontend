package com.example.android.weatherapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


import com.example.android.weatherapp.cities.CitiesAdapter;
import com.example.android.weatherapp.databinding.ActivityListOfCitiesBinding;
import com.example.android.weatherapp.models.Cities;
import com.example.android.weatherapp.models.City;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.android.weatherapp.models.Cities.getCities;


// Activity that displays the cities when we want to search
public class ListOfCitiesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SortedListAdapter.Callback {

    private CitiesAdapter mAdapter;
    private ActivityListOfCitiesBinding mBinding;
    private Animator mAnimator;

    private List<City> mModels;


    private static final Comparator<City> ALPHABETICAL_COMPARATOR = new Comparator<City>() {
        @Override
        public int compare(City a, City b) {
            return a.getName().compareTo(b.getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_of_cities);

        setSupportActionBar(mBinding.searchToolbar);

        mAdapter = new CitiesAdapter(this, ALPHABETICAL_COMPARATOR, new CitiesAdapter.Listener() {
            @Override
            public void onExampleModelClicked(City model) {
            //TODO: On Click Logic
            }
        });

        mAdapter.addCallback(this);

        mBinding.listOfCities.setLayoutManager(new LinearLayoutManager(this));
        mBinding.listOfCities.setAdapter(mAdapter);

        mModels = Cities.getCities();
        mAdapter.edit()
                .replaceAll(mModels)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<City> filteredModelList = filter(mModels, query);
        mAdapter.edit()
                .replaceAll(filteredModelList)
                .commit();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<City> filter(List<City> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<City> filteredModelList = new ArrayList<>();
        for (City model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    @Override
    public void onEditStarted() {
        if (mBinding.editProgressBar.getVisibility() != View.VISIBLE) {
            mBinding.editProgressBar.setVisibility(View.VISIBLE);
            mBinding.editProgressBar.setAlpha(0.0f);
        }

        if (mAnimator != null) {
            mAnimator.cancel();
        }

        mAnimator = ObjectAnimator.ofFloat(mBinding.editProgressBar, View.ALPHA, 1.0f);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.start();

        mBinding.listOfCities.animate().alpha(0.5f);
    }

    @Override
    public void onEditFinished() {
        mBinding.listOfCities.scrollToPosition(0);
        mBinding.listOfCities.animate().alpha(1.0f);

        if (mAnimator != null) {
            mAnimator.cancel();
        }

        mAnimator = ObjectAnimator.ofFloat(mBinding.editProgressBar, View.ALPHA, 0.0f);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {

            private boolean mCanceled = false;

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                mCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!mCanceled) {
                    mBinding.editProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mAnimator.start();
    }
}
