package com.example.android.handytest.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marianamarcondes on 5/17/17.
 */

public class Place implements Parcelable {
    private String mPlaceName, mPlaceDescription, mPlaceURL;

    public Place() {
    }


    public String getPlaceName() {
        return mPlaceName;
    }

    public void setPlaceName(String placeName) {
        mPlaceName = placeName;
    }

    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        mPlaceDescription = placeDescription;
    }

    public String getPlaceURL() {
        return mPlaceURL;
    }

    public void setPlaceURL(String placeURL) {
        mPlaceURL = placeURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPlaceName);
        dest.writeString(mPlaceDescription);
        dest.writeString(mPlaceURL);
    }

    protected Place(Parcel in) {
        mPlaceName = in.readString();
        mPlaceDescription = in.readString();
        mPlaceURL = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
