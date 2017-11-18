package com.teamgym.fitgym.fragments.personaltrainer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesTrainerFragment extends Fragment {


    public ActivitiesTrainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities_trainer, container, false);
    }

}
