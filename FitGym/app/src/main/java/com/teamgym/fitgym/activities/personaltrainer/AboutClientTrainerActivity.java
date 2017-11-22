package com.teamgym.fitgym.activities.personaltrainer;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import com.teamgym.fitgym.network.ClientApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

public class AboutClientTrainerActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView usernameTextView;
    TextView emailTextView;
    TextView addressTextView;
    TextView genderTextView;
    TextView birthDateTextView;
    TextView createdAtTextView;
    TextView heightTextView;
    ANImageView photoANImageView;
    Client client;
    Client imageClient;
    String tkn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_client_trainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        client = Client.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editClientButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        photoANImageView = (ANImageView) findViewById(R.id.photoANImageView);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        birthDateTextView = (TextView) findViewById(R.id.birthDateTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        createdAtTextView = (TextView) findViewById(R.id.createdAtTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        setSupportActionBar(toolbar);
        toolbar.setTitle(client.getShortFullName());
        toolbar.setSubtitle("Personal Trainer");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateFields();
    }

    private void updateFields() {
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(client.getShortFullName());
        photoANImageView.setDefaultImageResId(R.mipmap.ic_ptrainer_icon);
        photoANImageView.setErrorImageResId(R.mipmap.ic_ptrainer_icon);
        if(!client.getPhotoUrl().isEmpty())
            photoANImageView.setImageUrl(client.getPhotoUrl());
        usernameTextView.setText(client.getUsername());
        emailTextView.setText(client.getEmail());
        birthDateTextView.setText(client.getBirthDateAsString());
        genderTextView.setText(client.getGenderAsFullyString());
        addressTextView.setText(client.getAddress());
        heightTextView.setText(client.getHeight().toString());
        createdAtTextView.setText(client.getCreatedAtAsString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (client == null || imageClient == null) return;
        ClientApiService.getClient(tkn, client.getId(), new IActionPostServiceResult<Client>() {
            @Override
            public void execute(Client clientResult) {
                if (!imageClient.equals(clientResult)) {
                    client = clientResult;
                    client.setpTrainer(imageClient.getpTrainer());
                    updateFields();
                }
            }
        });
    }
}
