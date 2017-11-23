package com.teamgym.fitgym.adapters.personaltrainer;

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
import com.teamgym.fitgym.activities.personaltrainer.AboutClientTrainerActivity;
import com.teamgym.fitgym.activities.personaltrainer.ClientRoutinesActivity;
import com.teamgym.fitgym.fragments.personaltrainer.ClientsTrainerFragment;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.network.ClientApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.List;

/**
 * Created by Usuario on 18/11/2017.
 */

public class ClientsPTrainerAdapter extends RecyclerView.Adapter<ClientsPTrainerAdapter.ViewHolder> {
        private List<Client> clients ;
        private Client imageClient;
        private int currentPosition = 0;
        private int currentId = -1;
        private String tkn;

        public ClientsPTrainerAdapter(List<Client> clients) {
            this.clients = clients;
        }

        public ClientsPTrainerAdapter() {
        }

        public String getTkn() {
            return tkn;
        }

        public void setTkn(String tkn) {
            this.tkn = tkn;
        }

    @Override
        public ClientsPTrainerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.card_trainer_client,parent,false));
        }

        @Override
        public void onBindViewHolder(ClientsPTrainerAdapter.ViewHolder holder, int position) {
            final Client client =  clients.get(position);
            holder.photoANImageView.setErrorImageResId(R.drawable.client);
            holder.photoANImageView.setDefaultImageResId(R.drawable.client);
            holder.photoANImageView.setImageUrl(client.getPhotoUrl());
            holder.fullNameTextView.setText(client.getFullName());
            holder.usernameTextView.setText(client.getUsername());
            if(holder.fullNameTextView.getText().length() > 24)
                holder.fullNameTextView.setText(client.getFullName().substring(0, 19).concat("..."));
            holder.genderImageView.setImageResource((client.getGender().equalsIgnoreCase("M"))
                    ? R.drawable.ic_man_symbol_blue_24dp : R.drawable.ic_female_symbol_pink_24dp);
            holder.moreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AboutClientTrainerActivity.class);
                    intent.putExtras(client.toBundle());
                    intent.putExtra("token", tkn);
                    imageClient = client;
                    currentPosition = position;
                    currentId = client.getId();
                    context.startActivity(intent);
                }
            });
            holder.foodImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            holder.routinesImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClientRoutinesActivity.class);
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

        public ClientsPTrainerAdapter setClients(List<Client> clients) {
            this.clients = clients;
            return this;
        }

        public List<Client> getClients(){
            return  clients;
        }

        public ClientsPTrainerAdapter verifyIfItemChanged(String tkn) {
            if (clients.size() == 0) return this;
            if (currentId == -1 || imageClient == null) return this;
            ClientApiService.getClient(tkn, currentId, new IActionPostServiceResult<Client>() {
                @Override
                public void execute(Client client) {
                    if (!imageClient.equals(client)) {
                        client.setpTrainer(imageClient.getpTrainer());
                        clients.set(currentPosition, client);
                        notifyItemChanged(currentPosition);
                    }
                }
            });
            return this;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ANImageView photoANImageView;
            TextView usernameTextView;
            TextView fullNameTextView;
            ImageView genderImageView;
            TextView moreTextView;
            ImageView routinesImageView;
            ImageView foodImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                photoANImageView = (ANImageView) itemView.findViewById(R.id.photoANImageView);
                fullNameTextView = (TextView) itemView.findViewById(R.id.fullNameTextView);
                usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
                genderImageView = (ImageView) itemView.findViewById(R.id.genderImageView);
                routinesImageView = (ImageView) itemView.findViewById(R.id.routinesImageView);
                foodImageView = (ImageView) itemView.findViewById(R.id.foodImageView);
                moreTextView = (TextView) itemView.findViewById(R.id.aboutTextView);
            }
        }
}
