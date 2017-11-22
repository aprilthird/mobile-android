package com.teamgym.fitgym.adapters.gymcompany;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymcompany.AboutEstablishmentActivity;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.List;

/**
 * Created by Usuario on 14/11/2017.
 */

public class EstablishmentsAdapter extends RecyclerView.Adapter<EstablishmentsAdapter.ViewHolder> {
    private List<Establishment> establishments;
    private Establishment imageEstablishment;
    private int currentPosition = 0;
    private int currentId = -1;
    private String tkn;

    public EstablishmentsAdapter() {
    }

    public EstablishmentsAdapter(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    @Override
    public EstablishmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_establishment, parent, false));
    }

    @Override
    public void onBindViewHolder(EstablishmentsAdapter.ViewHolder holder, int position) {
        Establishment establishment = establishments.get(position);

        holder.imageImageView.setImageResource(R.mipmap.ic_establishment_icon);
        holder.nameTextView.setText(establishment.getName());
        holder.moreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                Context context = view.getContext();
                Intent intent = new Intent(context, AboutEstablishmentActivity.class);
                intent.putExtras(establishment.toBundle());
                intent.putExtra("token", tkn);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return establishments.size();
    }

    public EstablishmentsAdapter verifyIfItemChanged(String tkn) {
        if(establishments.isEmpty()) return this;
        if(imageEstablishment == null || currentId == -1) return this;
        EstablishmentApiService.getEstablishment(tkn, currentId, new IActionPostServiceResult<Establishment>() {
            @Override
            public void execute(Establishment establishment) {
                if (!imageEstablishment.equals(establishment)) {
                    establishment.setGymCompany(imageEstablishment.getGymCompany());
                    establishments.set(currentPosition, establishment);
                    notifyItemChanged(currentPosition);
                }
            }
        });
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageImageView;
        TextView nameTextView;
        TextView moreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            moreTextView = (TextView) itemView.findViewById(R.id.moreTextView);
        }
    }
}
