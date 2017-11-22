package com.teamgym.fitgym.activities.gymcompany;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AddEditPersonalTrainerActivity extends AppCompatActivity {
    static final String[] FULL_GENDERS = new String[] { "Female", "Male" };
    static final String[] ABV_GENDERS = new String[] { "F", "M" };

    static final int DATE_DIALOG_ID = 0;
    TextInputEditText firstNameEditText;
    TextInputEditText lastNameEditText;
    TextInputEditText phoneNumberEditText;
    TextInputEditText addressEditText;
    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    TextInputEditText confirmPasswordEditText;
    BetterSpinner genderSpinner;
    AppCompatButton birthDateButton;
    int mYear, mMonth, mDay;
    String tkn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_personal_trainer);
        PTrainer trainer = PTrainer.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");

        firstNameEditText = (TextInputEditText) findViewById(R.id.firstNameTextInputEditText);
        lastNameEditText = (TextInputEditText) findViewById(R.id.lastNameTextInputEditText);
        usernameEditText = (TextInputEditText) findViewById(R.id.usernameTextInputEditText);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        confirmPasswordEditText = (TextInputEditText) findViewById(R.id.confirmPasswordTextInputEditText);
        phoneNumberEditText = (TextInputEditText) findViewById(R.id.phoneNumberTextInputEditText);
        addressEditText = (TextInputEditText) findViewById(R.id.addressTextInputEditText);
        genderSpinner = (BetterSpinner) findViewById(R.id.genderSpinner);
        birthDateButton = (AppCompatButton) findViewById(R.id.birthDateButton);

        birthDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // Spinner saves a string array. [index position] and item selected (string)
        HashMap<Integer, String> genderMap = new HashMap<>();
        HashMap<String, Integer> genderMapInverse = new HashMap<>();
        HashMap<String, Integer> genderFullMapInverse = new HashMap<>();
        String[] spinnerElements = new String[FULL_GENDERS.length];

        // Counter 'i' will be a kind of temporal key
        // Thanks to its value (array index position) we can relate the KEY (independently stored) and the VALUE (assigned to the Spinner)
        for(int i = 0; i < FULL_GENDERS.length; ++i) {
            genderMap.put(i, ABV_GENDERS[i]); // Here We save the PRIMARY KEY. It can be obtained using the temporal key
            genderMapInverse.put(ABV_GENDERS[i], i); // For bi-directional access
            genderFullMapInverse.put(FULL_GENDERS[i], i); // For access to full genders
            spinnerElements[i] = FULL_GENDERS[i]; // Here We save the VALUES in the temporal key position (easy access) for the Spinner
        }

        // The string array of elements (VALUES) is assigned to the Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerElements);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinnerAdapter);

        // ADD Default Values
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        if(trainer != null) {
            // EDIT Default Values
            firstNameEditText.setText(trainer.getFirstName());
            lastNameEditText.setText(trainer.getLastName());
            usernameEditText.setText(trainer.getUsername());
            phoneNumberEditText.setText(trainer.getPhoneNumber());
            addressEditText.setText(trainer.getAddress());
            Calendar    birthDate = Calendar.getInstance();
            birthDate.setTime(trainer.getBirthDate());
            mYear = birthDate.get(Calendar.YEAR);
            mMonth = birthDate.get(Calendar.MONTH);
            mDay = birthDate.get(Calendar.DAY_OF_MONTH);
            int genderIndex = genderMapInverse.get(trainer.getGender());
            genderSpinner.setText(spinnerAdapter.getItem(genderIndex));
        }

        updateDisplay();

        FloatingActionButton fabSaveTrainer = (FloatingActionButton) findViewById(R.id.saveTrainerButton);
        fabSaveTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String birthDateString = birthDateButton.getText().toString();
                String gender = genderMap.get(genderFullMapInverse.get(genderSpinner.getText().toString()));
                if(gender == null) gender = "";

                boolean cancel = false;

                if(firstName.trim().isEmpty()) {
                    firstNameEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if(lastName.trim().isEmpty()) {
                    lastNameEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if(username.trim().isEmpty()) {
                    usernameEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if(password.trim().isEmpty()) {
                    passwordEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else if (password.length() < 6) {
                    passwordEditText.setError(String.format(getString(R.string.form_field_min_length), "6"));
                    cancel = true;
                }

                if(confirmPassword.trim().isEmpty()) {
                    confirmPasswordEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else if (!confirmPassword.equalsIgnoreCase(password)) {
                    confirmPasswordEditText.setError(getString(R.string.form_field_password_confirmation));
                    cancel = true;
                }

                if(phoneNumber.trim().isEmpty()) {
                    phoneNumberEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else if (phoneNumber.length() > 12) {
                    phoneNumberEditText.setError(String.format(getString(R.string.form_field_max_length), "12"));
                    cancel = true;
                }

                if(address.trim().isEmpty()) {
                    addressEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                if(gender.trim().isEmpty()) {
                    genderSpinner.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                Date currentDate = new Date();
                Date birthDate = currentDate;

                try {
                    birthDate = (new SimpleDateFormat("MM-dd-yyyy").parse(birthDateString));
                }
                catch (ParseException e) {
                    birthDateButton.setError(getString(R.string.form_field_invalid));
                    cancel = true;
                }

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTime(currentDate);
                cal2.setTime(birthDate);
                boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

                if(birthDate.after(currentDate) || sameDay) {
                    birthDateButton.setError(getString(R.string.form_field_invalid));
                    cancel = true;
                }

                if(cancel) return;

                PTrainer pTrainer = (trainer != null) ? trainer : new PTrainer();
                pTrainer.setFirstName(firstName)
                        .setLastName(lastName)
                        .setGender(gender)
                        .setUsername(username)
                        .setPassword(password)
                        .setAddress(address)
                        .setPhoneNumber(phoneNumber)
                        .setBirthDate(birthDate);

                if(trainer == null) {
                    GymCompany gymCompany = GymCompany.from(getIntent().getExtras());
                    pTrainer.setGymCompany(gymCompany);
                    pTrainer.setPhotoUrl("no-photo");
                    PTrainerApiService.createTrainer(tkn, pTrainer, new IActionPostServiceResult() {
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
                    PTrainerApiService.updateTrainer(tkn, pTrainer, new IActionPostServiceResult() {
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
        toolbar.setTitle(trainer == null ? "Add Trainer" : "Edit Trainer");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateDisplay() {
        this.birthDateButton.setText(
                new StringBuilder()
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
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
}
