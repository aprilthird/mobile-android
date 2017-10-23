package com.teamgym.fitgym.models;

import android.os.Bundle;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 24/09/2017.
 */

public class GymCompany{
    private int id;
    private String companyName;
    private String username;
    private String phoneNumber;
    private String createdAt;
    private String updatedAt;
    private String logoUrl;

    public GymCompany() {
    }

    public GymCompany(int id, String companyName, String username, String phoneNumber, String logoUrl) {
        this.id = id;
        this.companyName = companyName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.logoUrl = logoUrl;
    }

    public int getId() {
        return id;
    }

    public GymCompany setId(int id) {
        this.id = id;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public GymCompany setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public GymCompany setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public GymCompany setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public GymCompany setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public GymCompany setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public GymCompany setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("companyName", companyName);
        bundle.putString("username", username);
        bundle.putString("phoneNumber", phoneNumber);
        bundle.putString("createdAt", createdAt);
        bundle.putString("updatedAt", updatedAt);
        bundle.putString("logoUrl", logoUrl);
        return bundle;
    }

    public static GymCompany from(Bundle bundle) {
        GymCompany gymCompany = new GymCompany();
        gymCompany.setId(bundle.getInt("id"))
                .setCompanyName(bundle.getString("companyName"))
                .setUsername(bundle.getString("username"))
                .setPhoneNumber(bundle.getString("phoneNumber"))
                .setCreatedAt(bundle.getString("createdAt"))
                .setUpdatedAt(bundle.getString("updatedAt"))
                .setLogoUrl(bundle.getString("logoUrl"));
        return gymCompany;
    }

    public static GymCompany from(JSONObject jsonGymCompany) {
        GymCompany gymCompany = new GymCompany();
        try {
            gymCompany.setId(jsonGymCompany.getInt("gymCompanyId"))
                    .setCompanyName(jsonGymCompany.getString("name"))
                    .setUsername(jsonGymCompany.getString("username"))
                    .setPhoneNumber(jsonGymCompany.getString("phoneNumber"))
                    .setCreatedAt(jsonGymCompany.getString("createdAt"))
                    .setUpdatedAt(jsonGymCompany.getString("updatedAt"))
                    .setLogoUrl(jsonGymCompany.getString("urlLogo"));
            return gymCompany;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<GymCompany> from(JSONArray jsonGymCompanies) {
        List<GymCompany> gymCompanies = new ArrayList();
        for(int i = 0; i < jsonGymCompanies.length(); ++i) {
            try {
                gymCompanies.add(GymCompany.from(jsonGymCompanies.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gymCompanies;
    }
}
