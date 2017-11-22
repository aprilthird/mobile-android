package com.teamgym.fitgym.adapters.gymcompany;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymcompany.AboutClientActivity;
import com.teamgym.fitgym.models.Client;

import java.util.List;

/**
 * Created by GNO on 27/09/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private List<Client> clients ;
    private String tkn;

    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
    }

    public ClientsAdapter(){
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
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
        holder.photoANImageView.setErrorImageResId(R.drawable.client);
        holder.photoANImageView.setDefaultImageResId(R.drawable.client);
        holder.photoANImageView.setImageUrl(client.getPhotoUrl());
        holder.fullNameTextView.setText(client.getFullName());
        holder.usernameTextView.setText(client.getUsername());
        holder.trainerNameTextView.setText(String.format(holder.trainerNameTextView.getContext().getString(R.string.trained_by_trainer), client.getpTrainer().getFirstName()));
        if(holder.fullNameTextView.getText().length() > 24)
            holder.fullNameTextView.setText(client.getFullName().substring(0, 19).concat("..."));
        holder.genderImageView.setImageResource((client.getGender().equalsIgnoreCase("M"))
                ? R.drawable.ic_man_symbol_blue_24dp : R.drawable.ic_female_symbol_pink_24dp);
        holder.moreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AboutClientActivity.class);
                intent.putExtras(client.toBundle());
                intent.putExtra("token", tkn);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public ClientsAdapter setClients(List<Client> clients) {
        this.clients = clients;
        return this;
    }

    public List<Client> getClients(){
        return  clients;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView photoANImageView;
        TextView usernameTextView;
        TextView fullNameTextView;
        TextView trainerNameTextView;
        ImageView genderImageView;
        TextView moreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            photoANImageView = (ANImageView) itemView.findViewById(R.id.photoANImageView);
            fullNameTextView = (TextView) itemView.findViewById(R.id.fullNameTextView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
            trainerNameTextView = (TextView) itemView.findViewById(R.id.trainerNameTextView);
            genderImageView = (ImageView) itemView.findViewById(R.id.genderImageView);
            moreTextView = (TextView) itemView.findViewById(R.id.moreTextView);
        }
    }
}