package com.teamgym.fitgym;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by Usuario on 21/11/2017.
 */

public class FitGymApp extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
