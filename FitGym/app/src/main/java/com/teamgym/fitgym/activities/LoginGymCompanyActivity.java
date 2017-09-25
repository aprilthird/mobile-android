package com.teamgym.fitgym.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.teamgym.fitgym.R;

import org.w3c.dom.Text;

public class LoginGymCompanyActivity extends AppCompatActivity {

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

}
