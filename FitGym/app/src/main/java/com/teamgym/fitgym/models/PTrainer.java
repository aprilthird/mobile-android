package com.teamgym.fitgym.models;

import android.os.Bundle;

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

public class PTrainer {
    int id;
    String firstName;
    String lastName;
    String username;
    String address;
    String phoneNumber;
    String gender;
    String photoUrl;
    String createdAt;
    String updatedAt;
    Date birthDate;
    GymCompany gymCompany;

    public  PTrainer(){
    }

    public PTrainer(int id, String firstName, String lastName, String username, String address, String phoneNumber, String gender, String photoUrl, GymCompany gymCompany) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public PTrainer setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public PTrainer setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public PTrainer setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public PTrainer setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public PTrainer setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("firstName", firstName);
        bundle.putString("lastName", lastName);
        bundle.putString("username", username);
        bundle.putString("address", address);
        bundle.putString("phoneNumber", phoneNumber);
        bundle.putString("gender", gender);
        bundle.putString("createdAt", createdAt);
        bundle.putString("updatedAt", updatedAt);
        bundle.putString("birthDate", birthDate.toString());
        bundle.putString("photoUrl", photoUrl);
        bundle.putBundle("gymCompany", gymCompany.toBundle());
        return bundle;
    }

    public static PTrainer from(Bundle bundle) {
        PTrainer pTrainer = new PTrainer();
        try {
            pTrainer.setId(bundle.getInt("id"))
                    .setFirstName(bundle.getString("firstName"))
                    .setLastName(bundle.getString("lastName"))
                    .setUsername(bundle.getString("username"))
                    .setAddress(bundle.getString("address"))
                    .setPhoneNumber(bundle.getString("phoneNumber"))
                    .setBirthDate(((new SimpleDateFormat("yyyy-MM-dd")).parse(bundle.getString("birthDate"))))
                    .setCreatedAt(bundle.getString("createdAt"))
                    .setUpdatedAt(bundle.getString("updatedAt"))
                    .setPhotoUrl(bundle.getString("photoUrl"))
                    .setGymCompany(GymCompany.from(bundle.getBundle("gymCompany")));
            return pTrainer;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PTrainer from(JSONObject jsonPTrainer) {
        PTrainer pTrainer = new PTrainer();
        try {
            pTrainer.setId(jsonPTrainer.getInt("personalTrainerId"))
                    .setFirstName(jsonPTrainer.getString("firstName"))
                    .setLastName(jsonPTrainer.getString("lastName"))
                    .setAddress(jsonPTrainer.getString("address"))
                    .setPhoneNumber(jsonPTrainer.getString("phoneNumber"))
                    .setUsername(jsonPTrainer.getString("username"))
                    .setGender(jsonPTrainer.getString("gender"))
                    .setCreatedAt(jsonPTrainer.getString("createdAt"))
                    .setUpdatedAt(jsonPTrainer.getString("updatedAt"))
                    .setBirthDate((new SimpleDateFormat("yyyy-MM-dd").parse(jsonPTrainer.getString("birthDate"))))
                    .setPhotoUrl(jsonPTrainer.getString("photoUrl"));
            return pTrainer;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PTrainer> from(JSONArray jsonPTrainers) {
        List<PTrainer> trainers = new ArrayList<>();
        for(int i = 0; i < jsonPTrainers.length(); ++i) {
            try {
                trainers.add(PTrainer.from(jsonPTrainers.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return trainers;
    }
}
