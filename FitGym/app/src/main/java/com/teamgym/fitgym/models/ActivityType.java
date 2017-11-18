package com.teamgym.fitgym.models;

import android.app.*;
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

public class ActivityType {
    private int id;
    private String description;
    private GymCompany gymCompany;

    public ActivityType() {
    }

    public String getDescription() {
        return description;
    }

    public ActivityType setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getId() {
        return id;
    }

    public ActivityType setId(int id) {
        this.id = id;
        return this;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public ActivityType setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
        return this;
    }

    public ActivityType(String description) {
        this.description = description;
    }

    public boolean equals(ActivityType activityType) {
        if (activityType == null) return false;
        return description.equals(activityType.description);
    }

    public static ActivityType from(Bundle bundle) {
        if (bundle == null) return null;
        ActivityType activityType = new ActivityType();
        activityType.setId(bundle.getInt("id"))
                .setDescription(bundle.getString("description"))
                .setGymCompany(GymCompany.from(bundle.getBundle("gymCompany")));
        return activityType;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("description", description);
        bundle.putBundle("gymCompany", gymCompany.toBundle());
        return bundle;
    }

    public static ActivityType from(JSONObject jsonActivityType) {
        ActivityType activityType = new ActivityType();
        try {
            activityType.setId(jsonActivityType.getInt("activityTypeId"))
                    .setDescription(jsonActivityType.getString("description"));
            return activityType;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ActivityType from(JSONObject jsonActivityType, GymCompany gymCompany) {
        ActivityType activityType = new ActivityType();
        try {
            activityType.setId(jsonActivityType.getInt("activityTypeId"))
                    .setDescription(jsonActivityType.getString("description"))
                    .setGymCompany(gymCompany);
            return activityType;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonActivityType = new JSONObject();
        try {
            jsonActivityType.put("description", description);
            return jsonActivityType;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ActivityType> from(JSONArray jsonActivityTypes) {
        List<ActivityType> activityTypes = new ArrayList<>();
        for (int i = 0; i < jsonActivityTypes.length(); ++i) {
            try {
                activityTypes.add(ActivityType.from(jsonActivityTypes.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activityTypes;
    }

    public static List<ActivityType> from(JSONArray jsonActivityTypes, GymCompany gymCompany) {
        List<ActivityType> activityTypes = new ArrayList<>();
        for (int i = 0; i < jsonActivityTypes.length(); ++i) {
            try {
                activityTypes.add(ActivityType.from(jsonActivityTypes.getJSONObject(i), gymCompany));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activityTypes;
    }
}
