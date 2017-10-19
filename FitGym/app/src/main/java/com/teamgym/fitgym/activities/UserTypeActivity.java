package com.teamgym.fitgym.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamgym.fitgym.R;

public class UserTypeActivity extends AppCompatActivity {

    Button buttonPTrainer;
    Button buttonGymCompany;
    Button buttonGymClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        buttonGymClient = (Button)findViewById(R.id.buttonClient);
        buttonPTrainer = (Button)findViewById(R.id.buttonPTrainer);
        buttonGymCompany = (Button)findViewById(R.id.buttonGymCompany);

        buttonPTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(UserTypeActivity.this, LoginTrainerActivity.class));
            }
        });

        buttonGymCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(UserTypeActivity.this,LoginGymCompanyActivity.class));
            }
        });


    }
}
