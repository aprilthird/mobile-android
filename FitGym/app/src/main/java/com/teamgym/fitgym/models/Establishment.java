package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by GNO on 26/09/2017.
 */

public class Establishment extends SugarRecord {
    String establishmentName;
    float locationX;
    float locationY;
    GymCompany gymCompany;
    public Establishment(){}
    public Establishment(String establishmentName, float locationX, float locationY, GymCompany gymCompany) {
        this.establishmentName = establishmentName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.gymCompany = gymCompany;
    }
    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public void setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
    }


}
