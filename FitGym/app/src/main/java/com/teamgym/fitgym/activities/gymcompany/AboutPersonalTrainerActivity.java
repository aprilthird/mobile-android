package com.teamgym.fitgym.activities.gymcompany;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;

public class AboutPersonalTrainerActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView usernameTextView;
    TextView phoneNumberTextView;
    TextView addressTextView;
    TextView genderTextView;
    TextView birthDateTextView;
    TextView createdAtTextView;
    ANImageView photoANImageView;
    PTrainer trainer;
    PTrainer imageTrainer;
    String tkn = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_personal_trainer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        trainer = PTrainer.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");

        FloatingActionButton fabEditTrainer = (FloatingActionButton) findViewById(R.id.editTrainerButton);
        fabEditTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditPersonalTrainerActivity.class);
                intent.putExtras(trainer.toBundle());
                intent.putExtra("token", tkn);
                imageTrainer = trainer;
                context.startActivity(intent);
            }
        });

        photoANImageView = (ANImageView) findViewById(R.id.photoANImageView);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        birthDateTextView = (TextView) findViewById(R.id.birthDateTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        createdAtTextView = (TextView) findViewById(R.id.createdAtTextView);
        setSupportActionBar(toolbar);
        toolbar.setTitle(trainer.getShortFullName());
        toolbar.setSubtitle("Personal Trainer");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateFields();
    }

    private void updateFields() {
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(trainer.getShortFullName());
        photoANImageView.setDefaultImageResId(R.mipmap.ic_ptrainer_icon);
        photoANImageView.setErrorImageResId(R.mipmap.ic_ptrainer_icon);
        if(!trainer.getPhotoUrl().isEmpty())
            photoANImageView.setImageUrl(trainer.getPhotoUrl());
        usernameTextView.setText(trainer.getUsername());
        phoneNumberTextView.setText(trainer.getPhoneNumber());
        birthDateTextView.setText(trainer.getBirthDateAsString());
        genderTextView.setText(trainer.getGenderAsFullyString());
        addressTextView.setText(trainer.getAddress());
        createdAtTextView.setText(trainer.getCreatedAtAsString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (trainer == null || imageTrainer == null) return;
        PTrainerApiService.getTrainer(tkn, trainer.getId(), new IActionPostServiceResult<PTrainer>() {
            @Override
            public void execute(PTrainer trainerResult) {
                if(!imageTrainer.equals(trainerResult)) {
                    trainer = trainerResult;
                    trainer.setGymCompany(imageTrainer.getGymCompany());
                    updateFields();
                }
            }
        });
    }
}
