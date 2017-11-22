package com.teamgym.fitgym.fragments.personaltrainer;


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
import com.teamgym.fitgym.adapters.personaltrainer.ClientsPTrainerAdapter;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.ClientApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsTrainerFragment extends Fragment {
    RecyclerView clientsRecyclerView;
    ClientsPTrainerAdapter clientsAdapter;
    RecyclerView.LayoutManager clientsLayoutManager;
    List<Client> clients;
    int clientsOldSize = 0;
    PTrainer pTrainer;
    String tkn = "";

    public ClientsTrainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients_trainer, container, false);

        Format dateFormat = android.text.format.DateFormat.getDateFormat(getActivity().getApplicationContext());
        String pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern();

        pTrainer = PTrainer.from(getActivity().getIntent().getExtras());
        tkn = getActivity().getIntent().getStringExtra("token");

        FloatingActionButton fabAddClient = (FloatingActionButton) view.findViewById(R.id.addClientButton);
        fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        clientsRecyclerView = (RecyclerView) view.findViewById(R.id.clientsTrainerRecyclerView);
        clients = new ArrayList<>();
        clientsAdapter = new ClientsPTrainerAdapter(clients);
        clientsAdapter.setTkn(tkn);
        clientsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        clientsRecyclerView.setLayoutManager(clientsLayoutManager);
        clientsRecyclerView.setAdapter(clientsAdapter);
        updateClients();
        return view;
    }

    private void updateClients() {
        ClientApiService.getClientsByTrainerId(tkn, pTrainer, new IActionPostServiceResult<List<Client>>() {
            @Override
            public void execute(List<Client> result) {
                clients = result;
                clientsAdapter.setClients(clients);
                clientsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        clientsAdapter.verifyIfItemChanged(tkn);
        if (clientsOldSize == 0) return;
        ClientApiService.getClientsByTrainerId(tkn, pTrainer, new IActionPostServiceResult<List<Client>>() {
            @Override
            public void execute(List<Client> result) {
                if (clientsOldSize != result.size()) {
                    clientsAdapter.setClients(result);
                    clientsAdapter.notifyItemInserted(clients.size() - 1);
                }
            }
        });
    }
}
