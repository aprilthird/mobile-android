package com.teamgym.fitgym.models;

import com.orm.SugarRecord;

/**
 * Created by Usuario on 24/09/2017.
 */

public class GymCompany extends SugarRecord{
    private String gymCompanyName;
    private String username;
    private String password;
    private String telephone;

    public GymCompany()
    {    }
    public GymCompany(String gymCompanyName,String username, String password, String telephone)
    {
        this.gymCompanyName=gymCompanyName;
        this.username = username;
        this.telephone = telephone;
        this.password = password;
    }
    public String getGymCompanyName() {
        return gymCompanyName;
    }

    public void setGymCompanyName(String gymCompanyName) {
        this.gymCompanyName = gymCompanyName;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
