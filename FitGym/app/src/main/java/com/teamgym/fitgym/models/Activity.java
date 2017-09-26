package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by GNO on 26/09/2017.
 */

public class Activity extends SugarRecord {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
