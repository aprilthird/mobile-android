package com.teamgym.fitgym.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Client;

import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    private List<Client> clients ;

    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.card_client,parent,false));
    }

    @Override
    public void onBindViewHolder(ClientAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public List<Client> getClients(){
        return  clients;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
        }
    }
}
