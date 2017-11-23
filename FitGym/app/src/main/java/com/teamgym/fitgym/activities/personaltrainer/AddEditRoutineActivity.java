package com.teamgym.fitgym.activities.personaltrainer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.ActivityApiService;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddEditRoutineActivity extends AppCompatActivity {
    static final int DATE_DIALOG_ID = 0;
    static final int START_TIME_DIALOG_ID = 1;
    static final int END_TIME_DIALOG_ID = 2;

    AppCompatButton dateButton;
    AppCompatButton startHourButton;
    AppCompatButton endHourButton;
    //BetterSpinner establishmentSpinner;
    int mYear, mMonth, mDay;
    int mStartHour, mStartMinutes;
    int mEndHour, mEndMinutes;
    String tkn;
    Activity activity;
    HashMap<Integer, String> establishmentMap = new HashMap<>();
    HashMap<String, Integer> establishmentMapInverse = new HashMap<>();
    List<String> spinnerElements;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_routine);

        activity = (getIntent().hasExtra("activity")) ? Activity.from(getIntent().getBundleExtra("activity")) : null;
        tkn = getIntent().getStringExtra("token");

        dateButton = (AppCompatButton) findViewById(R.id.dateButton);
        startHourButton = (AppCompatButton) findViewById(R.id.startHourButton);
        endHourButton = (AppCompatButton) findViewById(R.id.endHourButton);
        //establishmentSpinner = (BetterSpinner) findViewById(R.id.establishmentSpinner);

/*
        spinnerElements = new ArrayList<>();
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerElements);
        loadLSpinner();
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        establishmentSpinner.setAdapter(spinnerAdapter);
*/
        // ADD Default Values
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mStartHour = c.get(Calendar.HOUR_OF_DAY);
        mStartMinutes = c.get(Calendar.MINUTE);
        mEndHour = mStartHour + 1;
        mEndMinutes = mStartMinutes;

        if (activity != null) {
            Calendar date = Calendar.getInstance();
            date.setTime(activity.getStartTime());
            mYear = date.get(Calendar.YEAR);
            mMonth = date.get(Calendar.MONTH);
            mDay = date.get(Calendar.DAY_OF_MONTH);
            mStartHour = date.get(Calendar.HOUR_OF_DAY);
            mStartMinutes = date.get(Calendar.MINUTE);
            Calendar date2 = Calendar.getInstance();
            date2.setTime(activity.getEndTime());
            mEndHour = date2.get(Calendar.HOUR_OF_DAY);
            mEndMinutes = date2.get(Calendar.MINUTE);
        }

        updateDisplay();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime = mMonth + "-" + mDay + "-" + mYear + " " + mStartHour + ":" + mStartMinutes;
                String endTime = mMonth + "-" + mDay + "-" + mYear + " " + mEndHour + ":" + mEndMinutes;
                //String establishmentName = establishmentSpinner.getText().toString();
                int establishmentId;
                boolean cancel = false;

                /*
                if (establishmentName.trim().isEmpty()) {
                    establishmentSpinner.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
*/
                Date start = new Date();
                Date end = new Date();
                try {
                    start = (new SimpleDateFormat("MM-dd-yyyy HH:mm").parse(startTime));
                    end = (new SimpleDateFormat("MM-dd-yyyy HH:mm").parse(endTime));
                }
                catch (ParseException e) {
                    startHourButton.setError(getString(R.string.form_field_required));
                    endHourButton.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if (cancel) return;

                //establishmentId = establishmentMapInverse.get(establishmentName);
                Activity pActivity = (activity != null) ? activity : new Activity();
                Establishment establishment = new Establishment();
                establishment.setId(1);

                pActivity.setEstablishment(establishment)
                        .setStartTime(start)
                        .setEndTime(end);

                if (activity == null) {
                    Client client = Client.from(getIntent().getBundleExtra("client"));
                    pActivity.setClient(client);
                    ActivityApiService.createActivity(tkn, pActivity, new IActionPostServiceResult() {
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
                    ActivityApiService.updateActivity(tkn, pActivity, new IActionPostServiceResult() {
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

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        startHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(START_TIME_DIALOG_ID);
            }
        });

        endHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(END_TIME_DIALOG_ID);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((activity == null) ? "Add Routine" : "Edit Routine");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /*
    private void loadLSpinner() {
        GymCompany gymCompany = new GymCompany();
        if (activity == null) {
            gymCompany = GymCompany.from(getIntent().getBundleExtra("gymCompany"));
        }
        else {
            gymCompany = activity.getEstablishment().getGymCompany();
        }

        EstablishmentApiService.getEstablishmentsByGymId(tkn, gymCompany, new IActionPostServiceResult<List<Establishment>>() {
            @Override
            public void execute(List<Establishment> establishments) {
                spinnerElements.clear();
                for (Establishment e: establishments) {
                    establishmentMap.put(e.getId(), e.getName());
                    establishmentMapInverse.put(e.getName(), e.getId());
                    spinnerElements.add(e.getName());
                }
                spinnerAdapter.notifyDataSetChanged();

                if (activity != null) {
                    int establishmentIndex = spinnerElements.indexOf(activity.getEstablishment().getName());
                    establishmentSpinner.setText(spinnerAdapter.getItem(establishmentIndex));
                }
            }
        });
    }
*/

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
            case START_TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeStartSetListener,
                        mStartHour, mStartMinutes, true);
            case END_TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeEndSetListener,
                        mEndHour, mEndMinutes, true);
        }
        return null;
    }

    private void updateDisplay() {
        this.dateButton.setText(
                new StringBuilder()
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
        this.startHourButton.setText(
                new StringBuilder()
                    .append(mStartHour).append(":")
                    .append(mStartMinutes));
        this.endHourButton.setText(
                new StringBuilder()
                    .append(mEndHour).append(":")
                    .append(mEndMinutes));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeStartSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mStartHour = hourOfDay;
                    mStartMinutes = minute;
                    updateDisplay();
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeEndSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mEndHour = hourOfDay;
                    mEndMinutes = minute;
                    updateDisplay();
                }
            };
}
