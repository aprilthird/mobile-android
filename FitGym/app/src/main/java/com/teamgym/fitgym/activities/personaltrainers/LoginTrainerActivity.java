package com.teamgym.fitgym.activities.personaltrainers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;

public class LoginTrainerActivity extends AppCompatActivity {

    AppCompatButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_trainer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        signInButton = (AppCompatButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Start BottomNavigation Activity for Gym Company activities

            }
        });
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
