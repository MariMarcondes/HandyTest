package com.example.android.handytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.handytest.models.City;


public class RecyclerViewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_CITIESGUIDE = "cities guide";

    public RecyclerViewFragment() {
    }

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;


    public static RecyclerViewFragment newInstance(int sectionNumber, City[] citiesGuide) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putParcelableArray(ARG_CITIESGUIDE, citiesGuide);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Getting arguments
        int chosenCityNum = getArguments().getInt(ARG_SECTION_NUMBER);
        City[] mCitiesList = (City[]) getArguments().getParcelableArray(ARG_CITIESGUIDE);

        // Setting the RecyclerView
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        CitiesGuideAdapter adapter = new CitiesGuideAdapter(mCitiesList[chosenCityNum]);
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }
}
