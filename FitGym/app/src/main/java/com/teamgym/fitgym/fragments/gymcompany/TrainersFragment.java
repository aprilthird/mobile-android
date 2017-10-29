package com.teamgym.fitgym.fragments.gymcompany;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.PTrainersAdapter;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersFragment extends Fragment {
    RecyclerView trainersRecyclerView;
    PTrainersAdapter trainersAdapter;
    RecyclerView.LayoutManager trainersLayoutManager;
    List<PTrainer> trainers;
    GymCompany gymCompany;

    public TrainersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainers, container, false);
        trainersRecyclerView = (RecyclerView) view.findViewById(R.id.trainersRecyclerView);
        trainers = new ArrayList<>();
        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());
        trainersAdapter = new PTrainersAdapter(trainers);
        trainersLayoutManager = new GridLayoutManager(view.getContext(), 2);
        trainersRecyclerView.setAdapter(trainersAdapter);
        trainersRecyclerView.setLayoutManager(trainersLayoutManager);
        updateTrainers();
        return view;
    }

    private void updateTrainers() {
        PTrainerApiService.getTrainers(gymCompany.getId(), new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> result) {
                trainers = result;
                trainersAdapter.setTrainers(trainers);
                trainersAdapter.notifyDataSetChanged();
            }
        });
    }
}