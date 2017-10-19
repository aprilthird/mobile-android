package com.teamgym.fitgym.utilities;

import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.Goal;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.models.QMeasurement;
import com.teamgym.fitgym.models.SuscriptionType;
import com.teamgym.fitgym.models.TypeMeal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class DataService {

    public DataService()
    {
        /*
        if((GymCompany.listAll(GymCompany.class).isEmpty()))
        {
            GymCompany gym =new GymCompany("Gym1","dummygym","dummygym","4444444","dummygym@dummy.test","96 Lorem Ipsum Av");
            gym.save();
            PTrainer trainer = new PTrainer("Test","Trainer","dummygymtr1","test","89 Test Av","5555555",gym);
            trainer.save();
        }
        */
    }

    //Basic functions
    public List<ActivityType> getActivityTypes(){
        List<ActivityType> activityTypes = new ArrayList<>();
        //TODO query
        return  activityTypes;
    }

    public List<TypeMeal> getTypeMeals(){
        List<TypeMeal> typeMeals = new ArrayList<>();
        //TODO query
        return typeMeals;
    }

    public List<QMeasurement> getQMeasurement(){
        List<QMeasurement> qMeasurements =  new ArrayList<>();
        //TODO query
        return qMeasurements;
    }

    public List<SuscriptionType> getSuscriptionTypes(){
        List<SuscriptionType> suscriptionTypes = new ArrayList<>();
        //TODO query
        return suscriptionTypes;
    }



    //
    public List<Client> getPTrainerClients(PTrainer trainer){
        //TODO Test function
        return Client.find(Client.class,"pTrainer = ?", String.valueOf(1));
    }

    public List<Goal> getClientGoals(Client client){
        List<Goal> goals = new ArrayList<>();
        //TODO query
        return goals;
    }

    public boolean addClient(Client client)
    {
        try {
            client.save();
            return true;
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public boolean LoginTrainer()
    {
        //TODO populate data related to the user from backend on success
        return false;
    }

    public boolean LoginClient()
    {
        //TODO populate data related to the user from backend on success
        return false;
    }

    public boolean LoginGymCompany()
    {
        //TODO populate data related to the user from backend on success
        return false;
    }
    //TODO interface interactions functions
}
