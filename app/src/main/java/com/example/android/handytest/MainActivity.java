package com.example.android.handytest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.handytest.models.CitiesGuide;
import com.example.android.handytest.models.City;
import com.example.android.handytest.models.Place;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public Context mContext;
    public int NUM_OF_CITIES = 3;
    private CitiesGuide mCitiesGuide;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        // Checking user network availability
        if (isNetworkAvailable()) {
            requestJSON();
        } else {
            Toast.makeText(mContext, "Please check your network and try again", Toast.LENGTH_LONG).show();
            this.finishAffinity();
        }
    }

    public void requestJSON() {
        // Request using Volley
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url ="https://api.myjson.com/bins/1hgb3x";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String utf8String = new String();
                            mCitiesGuide = parseCities(response);
                            setTheRest();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    public CitiesGuide parseCities(String response) throws JSONException {
        CitiesGuide citiesGuide = new CitiesGuide();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray citiesArray = jsonObject.getJSONArray("cities");
        City[] cities = new City[NUM_OF_CITIES];
            for (int i = 0; i < citiesArray.length(); i++) {
                JSONObject jsonCity = citiesArray.getJSONObject(i);
                City city = new City();
                city.setCityName(jsonCity.getString("name"));
                city.setCityURL(jsonCity.getString("image"));
                JSONArray placesArray = jsonCity.getJSONArray("places");
                city.setCityPlaces(parsePlaces(placesArray));
                cities[i] = city;
            }
            citiesGuide.setCities(cities);
    return citiesGuide;
    }

    List<Place> parsePlaces(JSONArray jsonArray) {
        List<Place> places = new ArrayList<>();
        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject jsonPlace = null;
            try {
                jsonPlace = jsonArray.getJSONObject(j);
                Place place = new Place();
                place.setPlaceName(jsonPlace.getString("name"));
                place.setPlaceDescription(jsonPlace.getString("description"));
                place.setPlaceURL(jsonPlace.getString("image"));
                places.add(place);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return places;
    }

    // Those should only be set after the json request happens and all fields are populated
    public void setTheRest() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        City[] cities = mCitiesGuide.getCities();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), cities);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}