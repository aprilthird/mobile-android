package com.teamgym.fitgym.adapters.client;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.Establishment;

import java.util.List;

import static com.teamgym.fitgym.network.ClientApiService.client;

/**
 * Created by YANI on 05/11/2017.
 */

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {

    private List<Activity> activities;
    private List<ActivityType> activityTypes;
    private List<Establishment> establishments;

    public RoutinesAdapter(List<Activity> activities, List<ActivityType> activityTypes, List<Establishment> establishments) {
        this.activities = activities;
        this.activityTypes = activityTypes;
        this.establishments = establishments;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }



    public RoutinesAdapter() {
    }

    @Override
    public RoutinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.card_routines,parent,false));
    }

    @Override
    public void onBindViewHolder(RoutinesAdapter.ViewHolder holder, int position) {
        final Activity activity= activities.get(position);
        final Establishment establishment= establishments.get(position);
        final ActivityType activityType= activityTypes.get(position);
        holder.photoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.photoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.photoANImageView.setImageUrl(null);
        holder.exerciseTextView.setText(activityType.getDescription());
        holder.starttimeTextView.setText(activity.getStartTimeAsString());
        holder.endtimeTextView.setText(activity.getEndTimeAsString());
        holder.locationTextView.setText(establishment.getEstablishmentName());


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public List<ActivityType> getActivityTypes() {
        return activityTypes;
    }

    public void setActivityTypes(List<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ANImageView photoANImageView;
        TextView dayTextView;
        TextView exerciseTextView;
        TextView starttimeTextView;
        TextView endtimeTextView;
        TextView locationTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            photoANImageView= (ANImageView) itemView.findViewById(R.id.photoANImageView);
            dayTextView= (TextView) itemView.findViewById(R.id.dayTextView);
            exerciseTextView= (TextView) itemView.findViewById(R.id.exerciseTextView);
            starttimeTextView= (TextView) itemView.findViewById(R.id.starttimeTextView);
            endtimeTextView= (TextView) itemView.findViewById(R.id.endtimeTextView);
            locationTextView= (TextView) itemView.findViewById(R.id.locationTextView);


        }
    }
}
