package com.example.android.weatherapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;


import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


import com.example.android.weatherapp.cities.CitiesAdapter;
import com.example.android.weatherapp.databinding.ActivityListOfCitiesBinding;
import com.example.android.weatherapp.models.Cities;
import com.example.android.weatherapp.models.City;
import com.example.android.weatherapp.request.NetworkRequestQueue;
import com.example.android.weatherapp.request.cities.CitiesRequest;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.example.android.weatherapp.FirstLetterSelectionActivity.LETTER_EXTRA;


// Activity that displays the cities when we want to search

public class ListOfCitiesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SortedListAdapter.Callback, Observer {

    public static final String CITY_ID_EXTRA = "CITY ID" ;
    public static final int PICK_CITY = 1;
    public static final String CITY_NAME_EXTRA = "CITY NAME";
    public static final String CITY_COUNTRY_EXTRA = "CITY COUNTRY EXTRA";

    private CitiesAdapter mAdapter;
    private ActivityListOfCitiesBinding mBinding;
    private Animator mAnimator;


    private static final Comparator<City> ALPHABETICAL_COMPARATOR = new Comparator<City>() {
        @Override
        public int compare(City a, City b) {
            return a.getName().compareTo(b.getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_of_cities);

        setSupportActionBar(mBinding.searchToolbar);

        mAdapter = new CitiesAdapter(this, ALPHABETICAL_COMPARATOR, new CitiesAdapter.Listener() {
            @Override
            public void onExampleModelClicked(City model) {
            //TODO: On Click Logic
                Intent data = new Intent();
                data.putExtra(CITY_NAME_EXTRA,model.getName());
                data.putExtra(CITY_ID_EXTRA, model.getId());
                data.putExtra(CITY_COUNTRY_EXTRA, model.getCountry());

                Cities.getInstance().setCities(null);
                activity.setResult(RESULT_OK, data);
                activity.finish();
            }
        });

        mAdapter.addCallback(this);
        mBinding.listOfCities.setLayoutManager(new LinearLayoutManager(this));
        mBinding.listOfCities.setAdapter(mAdapter);
        mBinding.editProgressBar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String letter = intent.getStringExtra(LETTER_EXTRA);
        String serverAddr = intent.getStringExtra(IpAndPortSelectionActivity.SERVER_ADDR_EXTRA);

        Log.i("LETTER", letter);

        // This observable changes when the request gets the response
        Cities.getInstance().addObserver(this);

        try {
            // Send request to the server asking for the cities
            JSONObject params = new JSONObject();
            params.put("keyWord",letter.toLowerCase());
            CitiesRequest citiesRequest = new CitiesRequest(serverAddr, params, this);
            NetworkRequestQueue.getInstance(this).addToRequestQueue(citiesRequest);

            mAdapter.edit()
                    .replaceAll(Cities.getInstance().getCities())
                    .commit();
        } catch (Exception e) {

        }
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
        final List<City> filteredModelList = filter(Cities.getInstance().getCities(), query);
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

    @Override
    public void update(Observable observable, Object o) {
        mAdapter.edit()
                .replaceAll(Cities.getInstance().getCities())
                .commit();
        mBinding.editProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Cities.getInstance().setCities(null);
        super.onBackPressed();
    }
}
