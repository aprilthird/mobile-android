package com.teamgym.fitgym.activities.gymclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.BodyMeasurements;
import com.teamgym.fitgym.models.Client;

import org.w3c.dom.Text;

public class BodyMeasurementActivity extends AppCompatActivity {

    TextView armsTexview;
    TextView legsTextView;
    TextView absTextView;
    TextView chestTextView;
    TextView weightTextView;
    TextView heightTextView;


    BodyMeasurements bodym;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_measurement);

        armsTexview = (TextView) findViewById(R.id.armsTextView);
        legsTextView = (TextView) findViewById(R.id.legsTextView);
        absTextView = (TextView) findViewById(R.id.absTextView);
        chestTextView = (TextView) findViewById(R.id.chestTextView);
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);


        armsTexview.setText(bodym.getArms().toString());
        legsTextView.setText(bodym.getLegs().toString());
        absTextView.setText(bodym.getAbs().toString());
        chestTextView.setText(bodym.getChest().toString());
        weightTextView.setText(bodym.getWeight().toString());
        heightTextView.setText(client.getHeight().toString());


    }

    }

