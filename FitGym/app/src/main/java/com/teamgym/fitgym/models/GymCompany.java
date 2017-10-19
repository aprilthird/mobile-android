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
    private String address;
    private String logoUrl;

    public GymCompany() {
    }

    public GymCompany(int id, String companyName, String username, String phoneNumber, String address, String logoUrl) {
        this.id = id;
        this.companyName = companyName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.logoUrl = logoUrl;
    }

    public int getId() {
        return id;
    }

    public GymCompany setId(int id) {
        this.id = id;
        return this;
    }

    public String getcompanyName() {
        return companyName;
    }

    public GymCompany setcompanyName(String companyName) {
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

    public String getphoneNumber() {
        return phoneNumber;
    }

    public GymCompany setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public GymCompany setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public GymCompany setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("companyName", companyName);
        bundle.putString("username", username);
        bundle.putString("address", address);
        bundle.putString("phoneNumber", phoneNumber);
        bundle.putString("logoUrl", logoUrl);
        return bundle;
    }

    public static GymCompany from(Bundle bundle) {
        GymCompany gymCompany = new GymCompany();
        gymCompany.setId(bundle.getInt("id"))
                .setcompanyName(bundle.getString("companyName"))
                .setUsername(bundle.getString("username"))
                .setphoneNumber(bundle.getString("phoneNumber"))
                .setAddress(bundle.getString("address"))
                .setLogoUrl(" ");
        return gymCompany;
    }

    public static GymCompany from(JSONObject jsonGymCompany) {
        GymCompany gymCompany = new GymCompany();
        try {
            gymCompany.setId(jsonGymCompany.getInt("id"))
                    .setcompanyName(jsonGymCompany.getString("name"))
                    .setAddress(jsonGymCompany.getString("address"))
                    .setUsername(jsonGymCompany.getString("username"))
                    .setphoneNumber(jsonGymCompany.getString("phoneNumber"))
                    .setLogoUrl("");
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
