package com.teamgym.fitgym.fragments.client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.client.RoutinesAdapter;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.network.ClientApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutinesClientFragment extends Fragment {

    RecyclerView routineRecyclerView;
    RoutinesAdapter routinesAdapter;
    RecyclerView.LayoutManager routinesLayoutManager;
    List<Activity> activities;
    List<ActivityType> activityTypes;
    List<Establishment> establishments;

    public RoutinesClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ;
        View view= inflater.inflate(R.layout.fragment_routines_client, container, false);
        routineRecyclerView= (RecyclerView) view.findViewById(R.id.routinesRecyclerView);
        activities= new ArrayList<>();
        activityTypes= new ArrayList<>();
        establishments= new ArrayList<>();
        routinesAdapter= new RoutinesAdapter(activities,activityTypes,establishments);
        routinesLayoutManager= new GridLayoutManager(view.getContext(),2);
        routineRecyclerView.setAdapter(routinesAdapter);
        routineRecyclerView.setLayoutManager(routinesLayoutManager);

        return view;
    }

    /*private void updateRoutines()


    }*/

}
