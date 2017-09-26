package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by GNO on 26/09/2017.
 */

public class Goal extends SugarRecord {
    Date startTime,endTime;
    String description;
    BigDecimal arms,legs,abs,back,chest,weight;
    Client client;
    public Goal(){}
    public Goal(Date startTime, Date endTime, String description, BigDecimal arms, BigDecimal legs, BigDecimal abs, BigDecimal back, BigDecimal chest, BigDecimal weight, Client client) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.arms = arms;
        this.legs = legs;
        this.abs = abs;
        this.back = back;
        this.chest = chest;
        this.weight = weight;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getArms() {
        return arms;
    }

    public void setArms(BigDecimal arms) {
        this.arms = arms;
    }

    public BigDecimal getLegs() {
        return legs;
    }

    public void setLegs(BigDecimal legs) {
        this.legs = legs;
    }

    public BigDecimal getAbs() {
        return abs;
    }

    public void setAbs(BigDecimal abs) {
        this.abs = abs;
    }

    public BigDecimal getBack() {
        return back;
    }

    public void setBack(BigDecimal back) {
        this.back = back;
    }

    public BigDecimal getChest() {
        return chest;
    }

    public void setChest(BigDecimal chest) {
        this.chest = chest;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
