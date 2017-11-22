package com.teamgym.fitgym.models;

import android.os.Bundle;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class ActivityDetail {
    int id;
    Activity activity;
    int qRepetition;
    int activityTypeId;
    ActivityType activityType;
    String description;

    public ActivityDetail(){}
    public ActivityDetail(Activity activity, int qRepetition, ActivityType activityType) {
        this.activity = activity;
        this.qRepetition = qRepetition;
        this.activityType = activityType;
    }

    public int getId() {
        return id;
    }

    public ActivityDetail setId(int id) {
        this.id = id;
        return this;
    }

    public int getActivityTypeId() {
        return activityTypeId;
    }

    public ActivityDetail setActivityTypeId(int activityTypeId) {
        this.activityTypeId = activityTypeId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityDetail setDescription(String description) {
        this.description = description;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public ActivityDetail setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public int getqRepetition() {
        return qRepetition;
    }

    public ActivityDetail setqRepetition(int qRepetition) {
        this.qRepetition = qRepetition;
        return this;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public ActivityDetail setActivityType(ActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public boolean equals(ActivityDetail activityDetail){
        if (activityDetail == null) return false;
        return qRepetition == activityDetail.qRepetition
                && description.equals(activityDetail.getDescription())
                && activityType.getId() == activityDetail.getActivityType().getId();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("description", description);
        bundle.putInt("qRepetition", qRepetition);
        bundle.putBundle("activityType", activityType.toBundle());
        bundle.putBundle("activityDetail", activity.toBundle());
        return bundle;
    }

    public static ActivityDetail from(Bundle bundle) {
        ActivityDetail activityDetail = new ActivityDetail();
        activityDetail.setId(bundle.getInt("id"))
                .setqRepetition(bundle.getInt("qRepetition"))
                .setDescription(bundle.getString("description"))
                .setActivity(Activity.from(bundle.getBundle("activityDetail")))
                .setActivityType(ActivityType.from(bundle.getBundle("activityType")));
        return activityDetail;
    }

    public static ActivityDetail from(JSONObject jsonDetail) {
        ActivityDetail activityDetail = new ActivityDetail();
        try {
            activityDetail.setId(jsonDetail.getInt("activityDetailId"))
                    .setqRepetition(jsonDetail.getInt("qRepetition"))
                    .setDescription(jsonDetail.getString("description"));
            return activityDetail;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ActivityDetail from(JSONObject jsonDetail, Activity activity) {
        ActivityDetail activityDetail = new ActivityDetail();
        try {
            activityDetail.setId(jsonDetail.getInt("activityDetailId"))
                    .setqRepetition(jsonDetail.getInt("qRepetition"))
                    .setDescription(jsonDetail.getString("description"))
                    .setActivity(activity)
                    .setActivityTypeId(jsonDetail.getInt("activityTypeId"));
            return activityDetail;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonDetail = new JSONObject();
        try {
            jsonDetail.put("qRepetition", qRepetition)
                    .put("description", description)
                    .put("activityId", activity.getId())
                    .put("activityTypeId", activityType.getId());
            return jsonDetail;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ActivityDetail> from(JSONArray jsonDetails) {
        List<ActivityDetail> activityDetails = new ArrayList<>();
        for (int i = 0; i < jsonDetails.length(); ++i) {
            try {
                activityDetails.add(ActivityDetail.from(jsonDetails.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activityDetails;
    }

    public static List<ActivityDetail> from(JSONArray jsonDetails, Activity activity) {
        List<ActivityDetail> activityDetails = new ArrayList<>();
        for (int i = 0; i < jsonDetails.length(); ++i) {
            try {
                activityDetails.add(ActivityDetail.from(jsonDetails.getJSONObject(i), activity));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activityDetails;
    }
}