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
 * Created by GNO on 27/09/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private List<Client> clients ;

    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
    }

    public ClientsAdapter(){
    }

    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_client,parent,false));
    }

    @Override
    public void onBindViewHolder(ClientsAdapter.ViewHolder holder, int position) {
        final Client client =  clients.get(position);
        holder.nameTextView.setText(client.getFirstName()+" " + client.getLastName());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public ClientsAdapter setClients(List<Client> clients)
    {
        this.clients = clients;
        return this;
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

