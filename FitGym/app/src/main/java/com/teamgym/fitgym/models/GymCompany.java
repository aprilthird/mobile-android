package com.teamgym.fitgym.models;

/**
 * Created by Usuario on 24/09/2017.
 */

public class GymCompany {
    private int gymCompanyId;
    private String gymCompanyName;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String address;

    public GymCompany(int gymCompanyId, String gymCompanyName, String username, String password, String telephone, String email, String address) {
        this.gymCompanyId = gymCompanyId;
        this.gymCompanyName = gymCompanyName;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email=email;
        this.address=address;
    }

    public GymCompany() {
    }

    public int getGymCompanyId() {
        return gymCompanyId;
    }

    public GymCompany setGymCompanyId(int gymCompanyId) {
        this.gymCompanyId = gymCompanyId;
        return this;
    }

    public String getGymCompanyName() {
        return gymCompanyName;
    }

    public GymCompany setGymCompanyName(String gymCompanyName) {
        this.gymCompanyName = gymCompanyName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public GymCompany setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public GymCompany setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public GymCompany setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GymCompany setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
