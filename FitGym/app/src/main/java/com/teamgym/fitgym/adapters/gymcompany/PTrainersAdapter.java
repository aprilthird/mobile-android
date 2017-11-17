package com.teamgym.fitgym.adapters.gymcompany;

import android.app.Fragment;
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
import com.teamgym.fitgym.activities.gymcompany.AboutPersonalTrainerActivity;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;

import java.util.List;

/**
 * Created by Usuario on 22/10/2017.
 */

public class PTrainersAdapter extends RecyclerView.Adapter<PTrainersAdapter.ViewHolder> {
    private List<PTrainer> trainers;
    private PTrainer imageTrainer;
    private int currentPosition = 0;
    private int currentId = -1;

    public PTrainersAdapter() {
    }

    public PTrainersAdapter(List<PTrainer> trainers) {
        this.trainers = trainers;
    }

    public List<PTrainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<PTrainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public PTrainersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_trainer, parent, false));
    }

    @Override
    public void onBindViewHolder(PTrainersAdapter.ViewHolder holder, int position) {
        PTrainer trainer = trainers.get(position);

        holder.photoANImageView.setErrorImageResId(R.mipmap.ic_ptrainer_icon);
        holder.photoANImageView.setDefaultImageResId(R.mipmap.ic_ptrainer_icon);
        holder.photoANImageView.setImageUrl(trainer.getPhotoUrl());
        holder.fullNameTextView.setText(trainer.getFullName());
        holder.usernameTextView.setText(trainer.getUsername());
        if(holder.fullNameTextView.getText().length() > 24)
            holder.fullNameTextView.setText(trainer.getFullName().substring(0, 19).concat("..."));
        holder.genderImageView.setImageResource((trainer.getGender().equalsIgnoreCase("M"))
                ? R.drawable.ic_man_symbol_blue_24dp : R.drawable.ic_female_symbol_pink_24dp);
        holder.moreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AboutPersonalTrainerActivity.class);
                intent.putExtras(trainer.toBundle());
                imageTrainer = trainer;
                currentPosition = position;
                currentId = trainer.getId();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainers.size();
    }

    public PTrainersAdapter verifyIfItemChanged() {
        if (trainers.isEmpty()) return this;
        if (currentId == -1 || imageTrainer == null) return this;
        PTrainerApiService.getTrainer(currentId, new IActionPostServiceResult<PTrainer>() {
            @Override
            public void execute(PTrainer trainer) {
                if (!trainer.equals(imageTrainer)) {
                    trainer.setGymCompany(imageTrainer.getGymCompany());
                    trainers.set(currentPosition, trainer);
                    notifyItemChanged(currentPosition);
                }
            }
        });
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView photoANImageView;
        TextView fullNameTextView;
        TextView usernameTextView;
        ImageView genderImageView;
        TextView moreTextView;
        // TODO Rating Bar Trainer

        public ViewHolder(View itemView) {
            super(itemView);
            photoANImageView = (ANImageView) itemView.findViewById(R.id.photoANImageView);
            fullNameTextView = (TextView) itemView.findViewById(R.id.fullNameTextView);
            usernameTextView = (TextView) itemView.findViewById(R.id.usernameTextView);
            genderImageView = (ImageView) itemView.findViewById(R.id.genderImageView);
            moreTextView = (TextView) itemView.findViewById(R.id.moreTextView);
        }
    }
}
