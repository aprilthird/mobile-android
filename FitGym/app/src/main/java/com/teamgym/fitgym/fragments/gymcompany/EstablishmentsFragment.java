package com.teamgym.fitgym.fragments.gymcompany;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.gymcompany.EstablishmentsAdapter;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablishmentsFragment extends Fragment {
    RecyclerView establishmentsRecyclerView;
    EstablishmentsAdapter establishmentsAdapter;
    RecyclerView.LayoutManager establishmentsLayoutManager;
    List<Establishment> establishments;
    int establishmentsOldSize = 0;
    GymCompany gymCompany;

    public EstablishmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_establishments, container, false);

        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());

        FloatingActionButton fabAddEstablishment = (FloatingActionButton) view.findViewById(R.id.addEstablishmentButton);
        fabAddEstablishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        establishmentsRecyclerView = (RecyclerView) view.findViewById(R.id.establishmentsRecyclerView);
        establishments = new ArrayList<>();
        establishmentsAdapter = new EstablishmentsAdapter(establishments);
        establishmentsLayoutManager = new GridLayoutManager(view.getContext(), 1);
        establishmentsRecyclerView.setAdapter(establishmentsAdapter);
        establishmentsRecyclerView.setLayoutManager(establishmentsLayoutManager);
        updateEstablishments();
        return view;
    }

    private void updateEstablishments() {
        EstablishmentApiService.getEstablishmentsByGymId(gymCompany, new IActionPostServiceResult<List<Establishment>>() {
            @Override
            public void execute(List<Establishment> result) {
                establishments = result;
                establishmentsAdapter.setEstablishments(establishments);
                establishmentsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        establishmentsAdapter.verifyIfItemChanged();
        if (establishmentsOldSize == 0 || establishments.size() == 0) return;
        EstablishmentApiService.getEstablishmentsByGymId(gymCompany, new IActionPostServiceResult() {
            @Override
            public void execute(Object result) {
                if (establishmentsOldSize != establishments.size()) {
                    establishmentsAdapter.setEstablishments(establishments);
                    establishmentsAdapter.notifyItemInserted(establishments.size() - 1);
                }
            }
        });
    }
}