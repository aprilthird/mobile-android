package com.teamgym.fitgym.adapters.gymcompany;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.List;

/**
 * Created by Usuario on 14/11/2017.
 */

public class ActivityTypesAdapter extends RecyclerView.Adapter<ActivityTypesAdapter.ViewHolder>{
    private List<ActivityType> activityTypes;
    private ActivityType imageActivityType;
    private int currentPosition = 0;
    private int currentId = -1;

    public ActivityTypesAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_activity_type, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityType activityType = activityTypes.get(position);

        holder.imageImageView.setImageResource(getImageIdByDescription(activityType.getDescription()));
        holder.descriptionTextView.setText(activityType.getDescription());
        holder.moreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    private int getImageIdByDescription(String description) {
        switch (description) {
            case "Climbing":
            case "Spinning":
                return R.mipmap.ic_activity_climbing_icon;
            case "Cardio":
                return R.mipmap.ic_activity_cardio_icon;
            case "Machines":
            case "Machine":
            case "Maquinas":
                return R.mipmap.ic_activity_machines_icon;
            case "Aerobic":
            case "Aerobics":
            case "Aerobicos":
                return R.mipmap.ic_activity_aerobics_icon;
            default:
                return R.mipmap.ic_activity_default_icon;
        }
    }

    public ActivityTypesAdapter verifyIfItemChanged() {
        if(activityTypes.isEmpty()) return this;
        if(currentId == -1 || imageActivityType == null) return this;
        ActivityTypeApiService.getActivityType(currentId, new IActionPostServiceResult<ActivityType>() {
            @Override
            public void execute(ActivityType activityType) {
                if(!imageActivityType.equals(activityType)) {
                    activityType.setGymCompany(imageActivityType.getGymCompany());
                    activityTypes.set(currentPosition, activityType);
                    notifyItemChanged(currentPosition);
                }
            }
        });
        return this;
    }

    @Override
    public int getItemCount() {
        return activityTypes.size();
    }

    public ActivityTypesAdapter(List<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    public List<ActivityType> getActivityTypes() {
        return activityTypes;
    }

    public void setActivityTypes(List<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageImageView;
        TextView descriptionTextView;
        TextView moreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            moreTextView = (TextView) itemView.findViewById(R.id.moreTextView);
        }
    }
}
