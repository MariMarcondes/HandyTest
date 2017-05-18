package com.example.android.handytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.handytest.models.City;
import com.example.android.handytest.models.Place;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by marianamarcondes on 5/17/17.
 */

public class CitiesGuideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private City mChosenCity;
    List<Place> cityPlaces;


    public CitiesGuideAdapter(City city) {
        mChosenCity = city;
        cityPlaces = mChosenCity.getCityPlaces();
    }

    // This adapter has 2 View Holders, one for each element: City or Place

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        public ImageView mCityImage;

        public CitiesViewHolder(View itemView) {
            super(itemView);
            mCityImage = (ImageView) itemView.findViewById(R.id.cityImageIV);
        }

        public void bindCity(City city) {
            Picasso.with(itemView.getContext()).load(city.getCityURL()).into(mCityImage);
        }
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder {
        public TextView mPlaceNameLabel, mPlaceDescriptionLabel;
        public ImageView mPlaceImage;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            mPlaceNameLabel = (TextView) itemView.findViewById(R.id.nameTV);
            mPlaceDescriptionLabel = (TextView) itemView.findViewById(R.id.descriptionTV);
            mPlaceImage = (ImageView) itemView.findViewById(R.id.placeImageIV);
        }

        public void bindPlace(Place place) {
            mPlaceNameLabel.setText(place.getPlaceName());
            mPlaceDescriptionLabel.setText(place.getPlaceDescription());
            Picasso.with(itemView.getContext()).load(place.getPlaceURL()).into(mPlaceImage);
        }
    }

    // Checks if the element is a City or a Place
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return position;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_city, parent, false);
                CitiesViewHolder citiesViewHolder = new CitiesViewHolder(view);
                return citiesViewHolder;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_place, parent, false);
                PlacesViewHolder placesViewHolder = new PlacesViewHolder(view);
                return placesViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                CitiesViewHolder citiesViewHolder = (CitiesViewHolder) holder;
                ((CitiesViewHolder) holder).bindCity(mChosenCity);
                break;
            default:
                PlacesViewHolder placesViewHolder = (PlacesViewHolder) holder;
                ((PlacesViewHolder) holder).bindPlace(cityPlaces.get(position -1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cityPlaces.size() + 1;
    }
}
