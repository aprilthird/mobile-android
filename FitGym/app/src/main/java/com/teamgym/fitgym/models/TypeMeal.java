package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by GNO on 26/09/2017.
 */

public class TypeMeal extends SugarRecord {
    String description;

    public TypeMeal() {
    }

    public TypeMeal(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
