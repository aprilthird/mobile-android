package com.teamgym.fitgym.fragments.gymcompany;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.gymcompany.ClientsAdapter;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.ClientApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsFragment extends Fragment {
    RecyclerView clientsRecyclerView;
    ClientsAdapter clientsAdapter;
    RecyclerView.LayoutManager clientsLayoutManager;
    List<Client> clients;
    List<PTrainer> trainers;
    GymCompany gymCompany;
    String tkn;

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients, container, false);
        clientsRecyclerView = (RecyclerView) view.findViewById(R.id.clientsRecyclerView);
        trainers = new ArrayList<>();
        clients = new ArrayList<>();
        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());
        tkn = getActivity().getIntent().getStringExtra("token");
        clientsAdapter = new ClientsAdapter(clients);
        clientsAdapter.setTkn(tkn);
        clientsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        clientsRecyclerView.setAdapter(clientsAdapter);
        clientsRecyclerView.setLayoutManager(clientsLayoutManager);
        updateClients();
        return view;
    }

    private void updateClients() {
        PTrainerApiService.getTrainersByGymId(tkn, gymCompany.getId(), new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> result) {
                for(final PTrainer pt : result) {
                    ClientApiService.getClientsByTrainerId(tkn, pt, new IActionPostServiceResult<List<Client>>() {
                        @Override
                        public void execute(List<Client> result) {
                            clients.addAll(result);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                clients.stream().sorted(Comparator.comparing(Client::getCreatedAt));
                            clientsAdapter.setClients(clients);
                            clientsAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}
