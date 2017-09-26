package com.teamgym.fitgym.activities.clients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.teamgym.fitgym.R;

public class LoginClientActivity extends AppCompatActivity {


    AppCompatButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        signInButton = (AppCompatButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Start BottomNavigation Activity for Gym Company activities

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

/*
TextView visitPageTextView;
    AppCompatButton signInButton;
    AppCompatButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gym_company);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        visitPageTextView = (TextView) findViewById(R.id.visitPageTextView);

        visitPageTextView.setMovementMethod(LinkMovementMethod.getInstance());

        signInButton = (AppCompatButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Start BottomNavigation Activity for Gym Company activities

            }
        });

        signUpButton = (AppCompatButton) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Start Basic Activity for Gym Suscriptions

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

 */