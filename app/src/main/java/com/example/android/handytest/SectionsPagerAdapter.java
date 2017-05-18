package com.example.android.handytest;

/**
 * Created by marianamarcondes on 5/17/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.android.handytest.models.City;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    City[] mCities;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SectionsPagerAdapter(FragmentManager fm, City[] cities) {
        super(fm);
        mCities = cities;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a RecyclerViewFragment (defined as a static inner class below).
        return RecyclerViewFragment.newInstance(position, mCities);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    // Sets text on the tabs
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "São Paulo";
            case 1:
                return "Oxford";
            case 2:
                return "Hong Kong";
        }
        return null;
    }
}
