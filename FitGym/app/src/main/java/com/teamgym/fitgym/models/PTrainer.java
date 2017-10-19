package com.teamgym.fitgym.models;

import android.os.Bundle;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GNO on 26/09/2017.
 */

public class PTrainer {
    int id;
    String firstName;
    String lastName;
    String username;
    String address;
    String phoneNumber;
    String photoUrl;
    GymCompany gymCompany;

    public  PTrainer(){
    }

    public PTrainer(int id, String firstName, String lastName, String username, String address, String phoneNumber, String photoUrl, GymCompany gymCompany) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.gymCompany = gymCompany;
    }

    public int getId() {
        return id;
    }

    public PTrainer setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PTrainer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PTrainer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PTrainer setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PTrainer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PTrainer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public PTrainer setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public PTrainer setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("firstName", firstName);
        bundle.putString("lastName", lastName);
        bundle.putString("username", username);
        bundle.putString("address", address);
        bundle.putString("phoneNumber", phoneNumber);
        bundle.putString("photoUrl", photoUrl);
        bundle.putBundle("gymCompany", gymCompany.toBundle());
        return bundle;
    }

    public static PTrainer from(Bundle bundle) {
        PTrainer pTrainer = new PTrainer();
        pTrainer.setId(bundle.getInt("id"))
                .setFirstName(bundle.getString("firstName"))
                .setLastName(bundle.getString("lastName"))
                .setUsername(bundle.getString("username"))
                .setAddress(bundle.getString("address"))
                .setPhoneNumber(bundle.getString("phoneNumber"))
                .setPhotoUrl(bundle.getString("photoUrl"))
                .setGymCompany(GymCompany.from(bundle.getBundle("gymCompany")));
        return pTrainer;
    }

    public static PTrainer from(JSONObject jsonPTrainer) {
        PTrainer pTrainer = new PTrainer();
        try {
            pTrainer.setId(jsonPTrainer.getInt("id"))
                    .setFirstName(jsonPTrainer.getString("firstName"))
                    .setLastName(jsonPTrainer.getString("lastName"))
                    .setAddress(jsonPTrainer.getString("address"))
                    .setPhoneNumber(jsonPTrainer.getString("phoneNumber"))
                    .setUsername(jsonPTrainer.getString("username"))
                    .setPhotoUrl(" ");
            return pTrainer;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
