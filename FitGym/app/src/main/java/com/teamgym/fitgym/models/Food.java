package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;

/**
 * Created by GNO on 26/09/2017.
 */

public class Food extends SugarRecord {
    Diet diet;
    TypeMeal typeMeal;
    QMeasurement qMeasurement;

    public Food() {
    }

    public Food(Diet diet, TypeMeal typeMeal, QMeasurement qMeasurement, String nameFood, BigDecimal quantity, String description, BigDecimal calories) {

        this.diet = diet;
        this.typeMeal = typeMeal;
        this.qMeasurement = qMeasurement;
        this.nameFood = nameFood;
        this.quantity = quantity;
        this.description = description;
        this.calories = calories;
    }

    public Diet getDiet() {

        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public TypeMeal getTypeMeal() {
        return typeMeal;
    }

    public void setTypeMeal(TypeMeal typeMeal) {
        this.typeMeal = typeMeal;
    }

    public QMeasurement getqMeasurement() {
        return qMeasurement;
    }

    public void setqMeasurement(QMeasurement qMeasurement) {
        this.qMeasurement = qMeasurement;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    String nameFood;
    BigDecimal quantity;
    String description;
    BigDecimal calories;
}
