package com.teamgym.fitgym.fragments.gymcompany;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymcompany.AddEditPersonalTrainerActivity;
import com.teamgym.fitgym.adapters.gymcompany.PTrainersAdapter;
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
    int trainersOldSize = 0;
    GymCompany gymCompany;
    String tkn = "";

    public TrainersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainers, container, false);

        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());
        tkn = getActivity().getIntent().getStringExtra("token");

        FloatingActionButton fabAddTrainer = (FloatingActionButton) view.findViewById(R.id.addTrainerButton);
        fabAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditPersonalTrainerActivity.class);
                intent.putExtras(gymCompany.toBundle());
                intent.putExtra("token", tkn);
                trainersOldSize = trainers.size();
                context.startActivity(intent);
            }
        });

        trainersRecyclerView = (RecyclerView) view.findViewById(R.id.trainersRecyclerView);
        trainers = new ArrayList<>();
        trainersAdapter = new PTrainersAdapter(trainers);
        trainersAdapter.setTkn(tkn);
        trainersLayoutManager = new GridLayoutManager(view.getContext(), 2);
        trainersRecyclerView.setAdapter(trainersAdapter);
        trainersRecyclerView.setLayoutManager(trainersLayoutManager);
        updateTrainers();
        return view;
    }

    private void updateTrainers() {
        PTrainerApiService.getTrainersByGymId(tkn, gymCompany, new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> result) {
                trainers = result;
                trainersAdapter.setTrainers(trainers);
                trainersAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        trainersAdapter.verifyIfItemChanged(tkn);
        if (trainersOldSize == 0) return;
        PTrainerApiService.getTrainersByGymId(tkn, gymCompany, new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> trainers) {
                if(trainersOldSize != trainers.size()) {
                    trainersAdapter.setTrainers(trainers);
                    trainersAdapter.notifyItemInserted(trainers.size() - 1);
                }
            }
        });
    }
}