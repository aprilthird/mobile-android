package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by GNO on 26/09/2017.
 */

public class Client extends SugarRecord {
    String firstName , lastName, email, username, address, password;
    Date birthDate;
    BigDecimal height;
    PTrainer pTrainer;
    public Client(){}
    public Client(String firstName, String lastName, String email, String username, String address, String password, Date birthDate, BigDecimal height, PTrainer pTrainer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.address = address;
        this.password = password;
        this.birthDate = birthDate;
        this.height = height;
        this.pTrainer = pTrainer;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public PTrainer getpTrainer() {
        return pTrainer;
    }

    public void setpTrainer(PTrainer pTrainer) {
        this.pTrainer = pTrainer;
    }
}
