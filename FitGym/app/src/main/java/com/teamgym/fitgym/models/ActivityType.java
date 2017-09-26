package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by GNO on 26/09/2017.
 */

public class ActivityType extends SugarRecord {
    String description;
    public ActivityType(){}
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType(String description) {
        this.description = description;
    }
}
