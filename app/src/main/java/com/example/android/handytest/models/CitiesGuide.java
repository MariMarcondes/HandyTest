package com.example.android.handytest.models;

/**
 * Created by marianamarcondes on 5/17/17.
 */

public class CitiesGuide {
    private City[] mCities;

    public City[] getCities() {
        return mCities;
    }

    public void setCities(City[] cities) {
        mCities = new City[cities.length];
        for (int i = 0; i < cities.length; i++) {
            mCities[i] = cities[i];
        }
        mCities = cities;
    }
}
