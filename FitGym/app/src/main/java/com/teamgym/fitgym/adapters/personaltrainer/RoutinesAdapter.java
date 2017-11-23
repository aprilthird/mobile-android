package com.teamgym.fitgym.adapters.personaltrainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.personaltrainer.AddEditRoutineActivity;
import com.teamgym.fitgym.activities.personaltrainer.RoutineDetailsActivity;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.network.ActivityApiService;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.List;

/**
 * Created by Usuario on 22/11/2017.
 */

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {
    List<Activity> activities;
    Activity imageActivity = null;
    int currentId = -1;
    int currentPosition = 0;
    String tkn = "";

    public RoutinesAdapter(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    @Override
    public RoutinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_routines, parent, false));
    }

    @Override
    public void onBindViewHolder(RoutinesAdapter.ViewHolder holder, int position) {
        final Activity activity = activities.get(position);
        holder.dayTextView.setText(activity.getDay());
        holder.dateTextView.setText(activity.getDate());
        holder.locationTextView.setText(activity.getEstablishment().getName());
        holder.rangeTimeTextView.setText(activity.hourRangeAsString());
        holder.detailsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, RoutineDetailsActivity.class);
                intent.putExtras(activity.toBundle());
                intent.putExtra("token", tkn);
                context.startActivity(intent);
            }
        });
        holder.editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditRoutineActivity.class);
                intent.putExtra("token", tkn);
                intent.putExtra("activity", activity.toBundle());
                imageActivity = activity;
                currentId = activity.getId();
                currentPosition = position;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public RoutinesAdapter verifyIfItemChanged(String tkn) {
        if (activities.size() == 0) return this;
        if (currentId == -1 || imageActivity == null) return this;
        ActivityApiService.getActivity(tkn, currentId, new IActionPostServiceResult<Activity>() {
            @Override
            public void execute(Activity activity) {
                EstablishmentApiService.getEstablishment(tkn, activity.getEstablishmentId(), new IActionPostServiceResult<Establishment>() {
                    @Override
                    public void execute(Establishment establishment) {
                        activity.setEstablishment(establishment);
                        if (!imageActivity.equals(activity)) {
                            activity.setClient(imageActivity.getClient());
                            activities.set(currentPosition, activity);
                            notifyItemChanged(currentPosition);
                        }
                    }
                });
            }
        });
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;
        TextView dateTextView;
        TextView locationTextView;
        TextView rangeTimeTextView;
        TextView detailsTextView;
        TextView editTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            dayTextView = (TextView) itemView.findViewById(R.id.dayTextView);
            locationTextView = (TextView) itemView.findViewById(R.id.locationTextView);
            rangeTimeTextView = (TextView) itemView.findViewById(R.id.rangeTimeTextView);
            detailsTextView = (TextView) itemView.findViewById(R.id.detailsTextView);
            editTextView = (TextView) itemView.findViewById(R.id.editTextView);
        }
    }
}
