package com.teamgym.fitgym.activities.personaltrainer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.PTrainer;

public class AboutClientTrainerActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView usernameTextView;
    TextView emailTextView;
    TextView addressTextView;
    TextView genderTextView;
    TextView birthDateTextView;
    TextView createdAtTextView;
    ANImageView photoANImageView;
    Client client;
    Client imageClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_client_trainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        client = Client.from(getIntent().getExtras());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toolbar.setTitle(client.getShortFullName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
