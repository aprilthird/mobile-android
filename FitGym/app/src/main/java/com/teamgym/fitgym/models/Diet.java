package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;

/**
 * Created by GNO on 26/09/2017.
 */

public class Diet {
    BigDecimal totalCalories;

    public Diet(){}
    public Diet(BigDecimal totalCalories) {
        this.totalCalories = totalCalories;
    }

    public BigDecimal getTotalCalories() {

        return totalCalories;
    }

    public void setTotalCalories(BigDecimal totalCalories) {
        this.totalCalories = totalCalories;
    }
}
