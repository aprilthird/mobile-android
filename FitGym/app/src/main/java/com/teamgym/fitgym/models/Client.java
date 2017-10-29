package com.teamgym.fitgym.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class Client {
    int id;
    String firstName , lastName, email, username, address, password, gender, photoUrl;
    Date birthDate;
    BigDecimal height;
    PTrainer pTrainer;
    String createdAt, updatedAt;

    public Client(){}

    public Client(String firstName, String lastName, String email, String username, String address, String password, String gender, Date birthDate, BigDecimal height, PTrainer pTrainer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.address = address;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.pTrainer = pTrainer;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Client setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Client setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Client setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Client setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Client setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public Client setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Client setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Client setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public PTrainer getpTrainer() {
        return pTrainer;
    }

    public Client setpTrainer(PTrainer pTrainer) {
        this.pTrainer = pTrainer;
        return this;
    }

    public int getId() {
        return id;
    }

    public Client setId(int id) {
        this.id = id;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("firstName", firstName);
        bundle.putString("lastName", lastName);
        bundle.putString("username", username);
        bundle.putString("birthDate", birthDate.toString());
        bundle.putString("address", address);
        bundle.putString("email", email);
        bundle.putString("gender", gender);
        bundle.putString("photoUrl", photoUrl);
        bundle.putString("height", height.toString());
        bundle.putString("createdAt", createdAt);
        bundle.putString("updatedAt", updatedAt);
        bundle.putBundle("trainer", pTrainer.toBundle());
        return bundle;
    }

    public static Client from(Bundle bundle) {
        Client client = new Client();
        try {
            client.setId(bundle.getInt("id"))
                    .setFirstName(bundle.getString("firstName"))
                    .setLastName(bundle.getString("lastName"))
                    .setUsername(bundle.getString("username"))
                    .setEmail(bundle.getString("email"))
                    .setGender(bundle.getString("gender"))
                    .setAddress(bundle.getString("address"))
                    .setPhotoUrl(bundle.getString("photoUrl"))
                    .setHeight(new BigDecimal(bundle.getString("height")))
                    .setBirthDate(((new SimpleDateFormat("yyyy-MM-dd")).parse(bundle.getString("birthDate"))))
                    .setpTrainer(PTrainer.from(bundle.getBundle("trainer")));
            return client;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Client from(JSONObject jsonClient, PTrainer trainer) {
        Client client = new Client();
        try {
            client.setId(jsonClient.getInt("clientId"))
                    .setFirstName(jsonClient.getString("firstName"))
                    .setLastName(jsonClient.getString("lastName"))
                    .setUsername(jsonClient.getString("username"))
                    .setEmail(jsonClient.getString("email"))
                    .setAddress(jsonClient.getString("address"))
                    .setGender(jsonClient.getString("gender"))
                    .setPhotoUrl(jsonClient.getString("photoUrl"))
                    .setHeight(BigDecimal.valueOf(jsonClient.getDouble("height")))
                    .setBirthDate((new SimpleDateFormat("yyyy-MM-dd").parse(jsonClient.getString("birthDate"))))
                    .setpTrainer(trainer);
            // TODO probar esto
            return client;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Client from(JSONObject jsonClient) {
        final Client client = new Client();
        try {
            client.setId(jsonClient.getInt("clientId"))
                    .setFirstName(jsonClient.getString("firstName"))
                    .setLastName(jsonClient.getString("lastName"))
                    .setUsername(jsonClient.getString("username"))
                    .setEmail(jsonClient.getString("email"))
                    .setAddress(jsonClient.getString("address"))
                    .setGender(jsonClient.getString("gender"))
                    .setPhotoUrl(jsonClient.getString("photoUrl"))
                    .setCreatedAt(jsonClient.getString("createdAt"))
                    .setUpdatedAt(jsonClient.getString("updatedAt"))
                    .setHeight(BigDecimal.valueOf(jsonClient.getDouble("height")))
                    .setBirthDate((new SimpleDateFormat("yyyy-MM-dd").parse(jsonClient.getString("birthDate"))));
            // TODO probar esto
            return client;
        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Client> from(JSONArray jsonClients, PTrainer trainer) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < jsonClients.length(); ++i) {
            try {
                clients.add(Client.from(jsonClients.getJSONObject(i), trainer));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }

    public static List<Client> from(JSONArray jsonClients) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < jsonClients.length(); ++i) {
            try {
                clients.add(Client.from(jsonClients.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }
}
