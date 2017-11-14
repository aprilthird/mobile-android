package com.teamgym.fitgym.activities.gymclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Client;

public class CredentialsActivity extends AppCompatActivity {

    TextView usernameTextView;
    TextView passwordTextView;

    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        usernameTextView= (TextView) findViewById(R.id.usernameTextView);
        passwordTextView= (TextView)  findViewById(R.id.passwordTextView);

        usernameTextView.setText(client.getUsername());
        passwordTextView.setText(client.getPassword());
    }
}
