package com.example.android.handytest.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marianamarcondes on 5/17/17.
 */

public class City implements Parcelable {
    private String mCityName, mCityURL;
    private List<Place> mCityPlaces;

    public City() {
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public String getCityURL() {
        return mCityURL;
    }

    public void setCityURL(String cityURL) {
        mCityURL = cityURL;
    }

    public List<Place> getCityPlaces() {
        return mCityPlaces;
    }

    public void setCityPlaces(List<Place> cityPlaces) {
        mCityPlaces = cityPlaces;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCityName);
        dest.writeString(mCityURL);
        dest.writeTypedList(mCityPlaces);

    }

    private City(Parcel in) {
        mCityName = in.readString();
        mCityURL = in.readString();
        mCityPlaces = new ArrayList<Place>();
        in.readTypedList(mCityPlaces, Place.CREATOR);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
