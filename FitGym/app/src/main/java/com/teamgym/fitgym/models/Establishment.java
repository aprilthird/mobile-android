package com.teamgym.fitgym.models;

import android.os.Bundle;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class Establishment {
    private int id;
    private String name;
    private double locationX;
    private double locationY;
    private Date createdAt;
    private GymCompany gymCompany;

    public Establishment(){}
    public Establishment(String name, double locationX, double locationY, GymCompany gymCompany) {
        this.name = name;
        this.locationX = locationX;
        this.locationY = locationY;
        this.gymCompany = gymCompany;
    }

    public int getId() {
        return id;
    }

    public Establishment setId(int id) {
        this.id = id;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Establishment setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getName() {
        return name;
    }

    public Establishment setName(String name) {
        this.name = name;
        return this;
    }

    public double getLocationX() {
        return locationX;
    }

    public Establishment setLocationX(double locationX) {
        this.locationX = locationX;
        return this;
    }

    public double getLocationY() {
        return locationY;
    }

    public Establishment setLocationY(double locationY) {
        this.locationY = locationY;
        return this;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public Establishment setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
        return this;
    }

    public String getCreatedAtAsString() {
        return (new SimpleDateFormat("EEE MMM dd, yyyy")).format(createdAt);
    }

    public boolean equals (Establishment establishment) {
        if(establishment == null) return false;
        return name.equals(establishment.getName())
                && locationX == establishment.getLocationX()
                && locationY == establishment.getLocationY();
    }

    public static Establishment from(Bundle bundle) {
        if (bundle == null) return null;
        Establishment establishment = new Establishment();
        try {
            establishment.setId(bundle.getInt("id"))
                    .setName(bundle.getString("name"))
                    .setLocationX(bundle.getDouble("locationX"))
                    .setLocationY(bundle.getDouble("locationY"))
                    .setCreatedAt((new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(bundle.getString("createdAt"))))
                    .setGymCompany(GymCompany.from(bundle.getBundle("gymCompany")));
            return establishment;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name", name);
        bundle.putDouble("locationX", locationX);
        bundle.putDouble("locationY", locationY);
        bundle.putString("createdAt", createdAt.toString());
        bundle.putBundle("gymCompany", gymCompany.toBundle());
        return bundle;
    }

    public static Establishment from (JSONObject jsonEstablishment) {
        Establishment establishment = new Establishment();
        try {
            establishment.setId(jsonEstablishment.getInt("establishmentId"))
                .setName(jsonEstablishment.getString("name"))
                .setLocationY(jsonEstablishment.getDouble("locationY"))
                .setLocationX(jsonEstablishment.getDouble("locationX"))
                .setCreatedAt((new SimpleDateFormat("yyyy-MM-dd").parse(jsonEstablishment.getString("createdAt"))));
            return establishment;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Establishment from (JSONObject jsonEstablishment, GymCompany gymCompany) {
        Establishment establishment = new Establishment();
        try {
            establishment.setId(jsonEstablishment.getInt("establishmentId"))
                .setName(jsonEstablishment.getString("name"))
                .setLocationY(jsonEstablishment.getDouble("locationY"))
                .setLocationX(jsonEstablishment.getDouble("locationX"))
                .setCreatedAt((new SimpleDateFormat("yyyy-MM-dd").parse(jsonEstablishment.getString("createdAt"))))
                .setGymCompany(gymCompany);
            return establishment;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJSONObject () {
        JSONObject jsonEstablishment = new JSONObject();
        try {
            jsonEstablishment.put("name", name)
                .put("locationX", locationX)
                .put("locationY", locationY);
            return jsonEstablishment;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Establishment> from(JSONArray jsonEstablishments) {
        List<Establishment> establishments = new ArrayList<>();
        for(int i = 0; i < jsonEstablishments.length(); ++i) {
            try {
                establishments.add(Establishment.from(jsonEstablishments.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return establishments;
    }

    public static List<Establishment> from(JSONArray jsonEstablishments, GymCompany gymCompany) {
        List<Establishment> establishments = new ArrayList<>();
        for(int i = 0; i < jsonEstablishments.length(); ++i) {
            try {
                establishments.add(Establishment.from(jsonEstablishments.getJSONObject(i), gymCompany));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return establishments;
    }
}
