package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by GNO on 26/09/2017.
 */

public class Suscription extends SugarRecord {
    private SuscriptionType suscriptionType;
    private GymCompany gymCompany;
    private int qMonth;
    private Date startDate;
    public Suscription(){}
    public Suscription(SuscriptionType suscriptionType, GymCompany gymCompany, int qMonth, Date startDate)
    {
        this.suscriptionType = suscriptionType;
        this.gymCompany = gymCompany;
        this.qMonth = qMonth;
        this.startDate = startDate;
    }
    public SuscriptionType getSuscriptionType() {
        return suscriptionType;
    }

    public void setSuscriptionType(SuscriptionType suscriptionType) {
        this.suscriptionType = suscriptionType;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public void setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
    }

    public int getqMonth() {
        return qMonth;
    }

    public void setqMonth(int qMonth) {
        this.qMonth = qMonth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
