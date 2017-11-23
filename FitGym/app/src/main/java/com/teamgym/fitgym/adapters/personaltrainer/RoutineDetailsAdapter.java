package com.teamgym.fitgym.adapters.personaltrainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.personaltrainer.AddEditRoutineDetailActivity;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityDetail;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.network.ActivityDetailApiService;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Usuario on 23/11/2017.
 */

public class RoutineDetailsAdapter extends RecyclerView.Adapter<RoutineDetailsAdapter.ViewHolder> {
    List<ActivityDetail> activityDetails;
    ActivityDetail imageDetail = null;
    int currentId = -1;
    int currentPosition = 0;
    String tkn = "";

    public RoutineDetailsAdapter(List<ActivityDetail> activityDetails) {
        this.activityDetails = activityDetails;
    }

    public List<ActivityDetail> getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(List<ActivityDetail> activityDetails) {
        this.activityDetails = activityDetails;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    @Override
    public RoutineDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_routine_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RoutineDetailsAdapter.ViewHolder holder, int position) {
        final ActivityDetail activityDetail = activityDetails.get(position);
        holder.activityTypeTextView.setText(activityDetail.getActivityType().getDescription());
        holder.descriptionTextView.setText(activityDetail.getDescription());
        holder.repetitionTextView.setText(String.valueOf(activityDetail.getqRepetition()) + " rep.");
        holder.imageImageView.setImageResource(getImageIdByActivityType(activityDetail.getActivityType()));
        holder.editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditRoutineDetailActivity.class);
                intent.putExtra("activityDetail", activityDetail.toBundle());
                intent.putExtra("token", tkn);
                currentId = activityDetail.getId();
                currentPosition = position;
                imageDetail = activityDetail;
                context.startActivity(intent);
            }
        });
    }

    private int getImageIdByActivityType(ActivityType activityType) {
        switch (activityType.getDescription()) {
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

    @Override
    public int getItemCount() {
        return activityDetails.size();
    }

    public RoutineDetailsAdapter verifyIfItemChanged(String tkn) {
        if (activityDetails.size() == 0) return this;
        if (currentId == -1 || imageDetail == null) return this;
        ActivityDetailApiService.getActivityDetail(tkn, currentId, new IActionPostServiceResult<ActivityDetail>() {
            @Override
            public void execute(ActivityDetail activityDetail) {
                ActivityTypeApiService.getActivityType(tkn, activityDetail.getActivityTypeId(), new IActionPostServiceResult<ActivityType>() {
                    @Override
                    public void execute(ActivityType activityType) {
                        activityDetail.setActivityType(activityType);
                        if (!imageDetail.equals(activityDetail)) {
                            activityDetail.setActivity(imageDetail.getActivity());
                            activityDetails.set(currentPosition, activityDetail);
                            notifyItemChanged(currentPosition);
                        }
                    }
                });
            }
        });
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageImageView;
        TextView activityTypeTextView;
        TextView descriptionTextView;
        TextView repetitionTextView;
        TextView editTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
            activityTypeTextView = (TextView) itemView.findViewById(R.id.activityTypeTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            repetitionTextView = (TextView) itemView.findViewById(R.id.repetitionsTextView);
            editTextView = (TextView) itemView.findViewById(R.id.editTextView);
        }
    }
}
