package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;

/**
 * Created by Usuario on 24/09/2017.
 */

public class SuscriptionType extends SugarRecord{
    private int qpTrainer; //cantidad entrenadores
    private int qEstablishment;
    private BigDecimal price; //
    private String description;
    private int qClient;

    public SuscriptionType(){}

    public SuscriptionType(int qpTrainer, int qEstablishment, BigDecimal price, String description, int qClient) {
        this.qpTrainer = qpTrainer;
        this.qEstablishment = qEstablishment;
        this.price = price;
        this.description = description;
        this.qClient = qClient;
    }

    public int getQpTrainer() {
        return qpTrainer;
    }

    public void setQpTrainer(int qpTrainer) {
        this.qpTrainer = qpTrainer;
    }

    public int getqEstablishment() {
        return qEstablishment;
    }

    public void setqEstablishment(int qEstablishment) {
        this.qEstablishment = qEstablishment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getqClient() {
        return qClient;
    }

    public void setqClient(int qClient) {
        this.qClient = qClient;
    }
}
