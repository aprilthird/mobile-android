package com.teamgym.fitgym.activities.personaltrainer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityDetail;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.ActivityDetailApiService;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddEditRoutineDetailActivity extends AppCompatActivity {
    TextInputEditText repetitionsEditText;
    TextInputEditText descriptionEditText;
    BetterSpinner activityTypeSpinner;
    ActivityDetail activityDetail;
    String tkn = "";
    HashMap<Integer, String> activityMap = new HashMap<>();
    HashMap<String, Integer> activityMapInverse = new HashMap<>();
    List<String> spinnerElements;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_routine_detail);

        activityDetail = (getIntent().hasExtra("activityDetail")) ?
                ActivityDetail.from(getIntent().getBundleExtra("activityDetail"))
                : null;

        tkn = getIntent().getStringExtra("token");

        repetitionsEditText = (TextInputEditText) findViewById(R.id.repetitionsTextInputEditText);
        descriptionEditText = (TextInputEditText) findViewById(R.id.descriptionTextInputEditText);
        activityTypeSpinner = (BetterSpinner) findViewById(R.id.activityTypeSpinner);

        spinnerElements = new ArrayList<>();
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerElements);
        loadSpinner();
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityTypeSpinner.setAdapter(spinnerAdapter);

        if (activityDetail != null) {
            repetitionsEditText.setText(String.valueOf(activityDetail.getqRepetition()));
            descriptionEditText.setText(activityDetail.getDescription());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repetitions = repetitionsEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String activitTypeDescription = activityTypeSpinner.getText().toString();
                int activityTypeId;
                int repetitionsInt = 1;
                boolean cancel = false;

                if (repetitions.trim().isEmpty()) {
                    repetitionsEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else {
                    try {
                        repetitionsInt = Integer.valueOf(repetitions);
                    }
                    catch (Exception e) {
                        repetitionsEditText.setError(getString(R.string.form_field_invalid));
                        cancel = true;
                    }
                }

                if (description.trim().isEmpty()) {
                    descriptionEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if (activitTypeDescription.trim().isEmpty()) {
                    activityTypeSpinner.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if (cancel) return;

                activityTypeId = activityMapInverse.get(activitTypeDescription);
                ActivityDetail pActivityDetail = (activityDetail != null) ? activityDetail : new ActivityDetail();
                ActivityType activityType = new ActivityType();
                activityType.setId(activityTypeId);
                pActivityDetail.setqRepetition(repetitionsInt)
                        .setDescription(description)
                        .setActivityType(activityType);

                if (activityDetail == null) {
                    Activity activity = Activity.from(getIntent().getBundleExtra("activity"));
                    pActivityDetail.setActivity(activity);
                    ActivityDetailApiService.createActivityDetail(tkn, pActivityDetail, new IActionPostServiceResult<ActivityDetail>() {
                        @Override
                        public void execute(ActivityDetail newActivityDetail) {
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
                    ActivityDetailApiService.updateActivityDetail(tkn, pActivityDetail, new IActionPostServiceResult() {
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(activityDetail == null ? "Add Task" : "Edit Task");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadSpinner() {
        GymCompany gymCompany = new GymCompany();
        if (activityDetail == null) {
            gymCompany = GymCompany.from(getIntent().getBundleExtra("gymCompany"));
        }
        else {
            gymCompany = activityDetail.getActivityType().getGymCompany();
        }

        ActivityTypeApiService.getActivityTypesByGymId(tkn, gymCompany, new IActionPostServiceResult<List<ActivityType>>() {
            @Override
            public void execute(List<ActivityType> activityTypes) {
                spinnerElements.clear();
                for(ActivityType a: activityTypes) {
                    activityMap.put(a.getId(), a.getDescription());
                    activityMapInverse.put(a.getDescription(), a.getId());
                    spinnerElements.add(a.getDescription());
                }
                spinnerAdapter.notifyDataSetChanged();

                if (activityDetail != null) {
                    int activityTypeIndex = spinnerElements.indexOf(activityDetail.getActivityType().getDescription());
                    activityTypeSpinner.setText(spinnerAdapter.getItem(activityTypeIndex));
                }
            }
        });
    }
}
