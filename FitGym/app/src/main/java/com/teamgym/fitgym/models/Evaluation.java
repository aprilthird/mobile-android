package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by GNO on 26/09/2017.
 */

public class Evaluation extends SugarRecord {
    Date currentDate;
    String comment;
    Activity activity;
    Diet diet;

    public Evaluation() {
    }

    public Evaluation(Date currentDate, String comment, Activity activity, Diet diet) {
        this.currentDate = currentDate;
        this.comment = comment;
        this.activity = activity;
        this.diet = diet;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }
}
