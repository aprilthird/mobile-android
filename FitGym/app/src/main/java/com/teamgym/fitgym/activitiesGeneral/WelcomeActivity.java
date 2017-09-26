package com.teamgym.fitgym.activitiesGeneral;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.teamgym.fitgym.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    public static int ToNextActivity =4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), UserTypeActivity.class));
            }
        }, ToNextActivity * 1000);
    }
}
