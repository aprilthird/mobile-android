package com.teamgym.fitgym.fragments.gymcompany;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.ClientsAdapter;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.networking.ClientApiService;
import com.teamgym.fitgym.networking.IActionPostServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsFragment extends Fragment {
    RecyclerView clientsRecyclerView;
    ClientsAdapter clientsAdapter;
    RecyclerView.LayoutManager clientsLayoutManager;
    List<Client> clients;

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients, container, false);
        clientsRecyclerView = (RecyclerView) view.findViewById(R.id.clientsRecyclerView);
        clients = new ArrayList<>();
        clientsAdapter = new ClientsAdapter(clients);
        clientsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        clientsRecyclerView.setAdapter(clientsAdapter);
        clientsRecyclerView.setLayoutManager(clientsLayoutManager);
        updateClients();
        return view;
    }

    private void updateClients() {
        ClientApiService.getClients(new IActionPostServiceResult<List<Client>>() {
            @Override
            public void execute(List<Client> result) {
                clients = result;
                clientsAdapter.setClients(clients);
                clientsAdapter.notifyDataSetChanged();
            }
        });
    }
}
