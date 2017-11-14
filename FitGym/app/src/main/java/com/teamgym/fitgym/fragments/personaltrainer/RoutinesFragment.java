package com.teamgym.fitgym.fragments.personaltrainer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.client.RoutinesAdapter;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.Establishment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutinesFragment extends Fragment {



    public RoutinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_routines, container, false);

        return view;
    }

}
