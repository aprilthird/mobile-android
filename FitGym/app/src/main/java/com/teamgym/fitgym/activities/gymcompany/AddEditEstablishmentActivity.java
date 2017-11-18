package com.teamgym.fitgym.activities.gymcompany;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

public class AddEditEstablishmentActivity extends AppCompatActivity {
    TextInputEditText nameTextInputEditText;
    TextInputEditText locationXTextInputEditText;
    TextInputEditText locationYTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_establishment);

        Establishment establishment = Establishment.from(getIntent().getExtras());

        nameTextInputEditText = (TextInputEditText) findViewById(R.id.nameTextInputEditText);
        locationXTextInputEditText = (TextInputEditText) findViewById(R.id.locationXTextInputEditText);
        locationYTextInputEditText = (TextInputEditText) findViewById(R.id.locationYTextInputEditText);

        if(establishment != null) {
            nameTextInputEditText.setText(establishment.getName());
            locationXTextInputEditText.setText(String.valueOf(establishment.getLocationX()));
            locationYTextInputEditText.setText(String.valueOf(establishment.getLocationY()));
        }

        FloatingActionButton fabSaveEstablishment = (FloatingActionButton) findViewById(R.id.saveEstablishmentButton);
        fabSaveEstablishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTextInputEditText.getText().toString();
                String latitudeString = locationYTextInputEditText.getText().toString();
                String longitudeString = locationXTextInputEditText.getText().toString();
                boolean cancel = false;

                if (name.trim().isEmpty()) {
                    nameTextInputEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }

                Double latitude = 0.0;
                if (latitudeString.trim().isEmpty()) {
                    locationYTextInputEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else {
                    try {
                        latitude = Double.valueOf(latitudeString);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        locationYTextInputEditText.setError(getString(R.string.form_field_invalid));
                        cancel = true;
                    }
                }

                Double longitude = 0.0;
                if (longitudeString.trim().isEmpty()) {
                    locationXTextInputEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                else {
                    try {
                        longitude = Double.valueOf(longitudeString);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        locationXTextInputEditText.setError(getString(R.string.form_field_invalid));
                        cancel = true;
                    }
                }

                if (cancel) return;

                Establishment pEstablishment = (establishment != null) ? establishment : new Establishment();
                pEstablishment.setName(name)
                        .setLocationX(longitude)
                        .setLocationY(latitude);

                if (establishment == null) {
                    GymCompany gymCompany = GymCompany.from(getIntent().getExtras());
                    pEstablishment.setGymCompany(gymCompany);
                    EstablishmentApiService.createEstablishment(pEstablishment, new IActionPostServiceResult<Establishment>() {
                        @Override
                        public void execute(Establishment establishment1) {
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
                    EstablishmentApiService.updateEstablishment(pEstablishment, new IActionPostServiceResult<Establishment>() {
                        @Override
                        public void execute(Establishment establishment1) {
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
        toolbar.setTitle(establishment != null ? "Edit Establishment" : "Add Establishment");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
