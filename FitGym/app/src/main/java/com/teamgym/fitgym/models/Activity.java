package com.teamgym.fitgym.models;

import android.os.Bundle;
import android.sax.EndElementListener;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by GNO on 26/09/2017.
 */

public class Activity {
    int id;
    int establishmentId;
    Client client;
    Date startTime, endTime;
    Establishment establishment;

    public Activity(){}
    public Activity(Client client, Date startTime, Date endTime, Establishment establishment) {
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
        this.establishment = establishment;
    }

    public int getId() {
        return id;
    }

    public Activity setId(int id) {
        this.id = id;
        return this;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public Activity setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Activity setClient(Client client) {
        this.client = client;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Activity setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Activity setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public Activity setEstablishment(Establishment establishment) {
        this.establishment = establishment;
        return this;
    }
    public String getStartTimeAsString() {

        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(startTime);
    }
    public String getEndTimeAsString() {

        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(endTime);
    }

    public String getDay() {
        return (new SimpleDateFormat("EEEE")).format(startTime);
    }

    public String getDate() {
        return (new SimpleDateFormat("dd, MMM")).format(startTime);
    }

    public String getDateAsString() {
        return (new SimpleDateFormat("MM - dd")).format(startTime);
    }

    public String hourRangeAsString() {
        String start = (new SimpleDateFormat("HH:mm")).format(startTime);
        String end = (new SimpleDateFormat("HH:mm")).format(endTime);
        return start + " - " + end;
    }

    public String getStartTimeAsJSONString() {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(startTime);
    }

    public String getEndTimeAsJSONString() {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(endTime);
    }

    public boolean equals(Activity activity) {
        if (activity == null) return false;
        return startTime.equals(activity.getStartTime())
                && endTime.equals(activity.getEndTime())
                && establishment.getId() == activity.getEstablishment().getId();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putLong("startTime", startTime.getTime());
        bundle.putLong("endTime", endTime.getTime());
        bundle.putBundle("client", client.toBundle());
        bundle.putBundle("establishment", establishment.toBundle());
        return bundle;
    }

    public static Activity from(Bundle bundle) {
        Activity activity = new Activity();
        Date startTimeD = new Date(), endTimeD = new Date();
        startTimeD.setTime(bundle.getLong("startTime"));
        endTimeD.setTime(bundle.getLong("endTime"));
        activity.setId(bundle.getInt("id"))
                .setStartTime(startTimeD)
                .setEndTime(endTimeD)
                .setClient(Client.from(bundle.getBundle("client")))
                .setEstablishment(Establishment.from(bundle.getBundle("establishment")));
        return activity;
    }

    public static Activity from(JSONObject jsonActivity, Client client) {
        Activity activity = new Activity();
        try {
            activity.setId(jsonActivity.getInt("activityId"))
                    .setStartTime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonActivity.getString("startTime"))))
                    .setEndTime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonActivity.getString("endTime"))))
                    .setClient(client)
                    .setEstablishmentId(jsonActivity.getInt("establishmentId"));
            return activity;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Activity from(JSONObject jsonActivity) {
        Activity activity = new Activity();
        try {
            activity.setId(jsonActivity.getInt("activityId"))
                    .setStartTime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonActivity.getString("startTime"))))
                    .setEndTime((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonActivity.getString("endTime"))))
                    .setEstablishmentId(jsonActivity.getInt("establishmentId"));
            return activity;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonActivity = new JSONObject();
        try {
            jsonActivity.put("startTime", getStartTimeAsJSONString())
                    .put("endTime", getEndTimeAsJSONString())
                    .put("establishmentId", establishment.getId())
                    .put("clientId", client.getId());
            return jsonActivity;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> from(JSONArray jsonActivities) {
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < jsonActivities.length(); ++i) {
            try {
                activities.add(Activity.from(jsonActivities.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activities;
    }

    public static List<Activity> from(JSONArray jsonActivities, Client client) {
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < jsonActivities.length(); ++i) {
            try {
                activities.add(Activity.from(jsonActivities.getJSONObject(i), client));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return activities;
    }
}
