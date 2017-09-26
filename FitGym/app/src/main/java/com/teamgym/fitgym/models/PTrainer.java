package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by GNO on 26/09/2017.
 */

public class PTrainer extends SugarRecord {
    String firstName, lastName, username,password, address, telephone;
    GymCompany gymCompany;
    public  PTrainer(){};

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public GymCompany getGymCompany() {
        return gymCompany;
    }

    public void setGymCompany(GymCompany gymCompany) {
        this.gymCompany = gymCompany;
    }

    public PTrainer(String firstName, String lastName, String username, String password, String address, String telephone, GymCompany gymCompany) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
        this.gymCompany = gymCompany;
    }
}
