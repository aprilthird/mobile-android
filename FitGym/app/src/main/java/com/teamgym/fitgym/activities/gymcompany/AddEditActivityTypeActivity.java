package com.teamgym.fitgym.activities.gymcompany;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

public class AddEditActivityTypeActivity extends AppCompatActivity {
    TextInputEditText descriptionTextInputEditText;
    String tkn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_activity_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityType activityType = ActivityType.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");
        descriptionTextInputEditText = (TextInputEditText) findViewById(R.id.descriptionTextInputEditText);

        if (activityType != null) {
            descriptionTextInputEditText.setText(activityType.getDescription());
        }

        FloatingActionButton fabSaveActivityType = (FloatingActionButton) findViewById(R.id.saveActivityTypeButton);
        fabSaveActivityType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = descriptionTextInputEditText.getText().toString();
                boolean cancel = false;

                if (description.trim().isEmpty()) {
                    descriptionTextInputEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if (cancel) return;

                ActivityType pActivityType = (activityType != null) ? activityType : new ActivityType();
                pActivityType.setDescription(description);

                if (activityType == null) {
                    GymCompany gymCompany = GymCompany.from(getIntent().getExtras());
                    pActivityType.setGymCompany(gymCompany);
                    ActivityTypeApiService.createActivityType(tkn, pActivityType, new IActionPostServiceResult() {
                        @Override
                        public void execute(Object result) {
                            Snackbar.make(view, getString(R.string.alert_create_success), Snackbar.LENGTH_LONG * 2)
                                    .setAction(getString(R.string.message_button_ok), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            finish();
                                        }
                                    }).show();
                        }
                    });
                }
                else {
                    ActivityTypeApiService.updateActivityType(tkn, pActivityType, new IActionPostServiceResult() {
                        @Override
                        public void execute(Object result) {
                            Snackbar.make(view, getString(R.string.alert_save_success), Snackbar.LENGTH_LONG * 2)
                                    .setAction(getString(R.string.message_button_ok), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            finish();
                                        }
                                    }).show();
                        }
                    });
                }
            }
        });
    }

}
