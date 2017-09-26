package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by GNO on 26/09/2017.
 */

public class ActivityDetail extends SugarRecord {
    Activity activity;
    int qRepetition;
    ActivityType activityType;
    public ActivityDetail(){}
    public ActivityDetail(Activity activity, int qRepetition, ActivityType activityType) {
        this.activity = activity;
        this.qRepetition = qRepetition;
        this.activityType = activityType;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getqRepetition() {
        return qRepetition;
    }

    public void setqRepetition(int qRepetition) {
        this.qRepetition = qRepetition;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
