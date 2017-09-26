package com.teamgym.fitgym.activitiesGymCompany;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teamgym.fitgym.R;

import java.util.List;

public class RegisterGymCompanyActivity extends AppCompatActivity {

    RecyclerView registerWPlannerRecycleView;
    /*List<SubscriptionType> subscriptionTypeList;*/
    Context context;
    EditText editTextName, editTextPhone, editTextEmail, editTextAddresss, editTextUser, editTextPass, editTextConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gym_company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
        public void populate(){

            editTextName = (EditText) findViewById(R.id.gymnameTextInputEditText);
            editTextPhone = (EditText) findViewById(R.id.phoneTextInputEditText);
            editTextEmail= (EditText) findViewById(R.id.emailTextInputEditText);
            editTextAddresss = (EditText) findViewById(R.id.addressTextInputEditText);

            editTextUser = (EditText) findViewById(R.id.userTextInputEditText);
            editTextPass = (EditText) findViewById(R.id.passwordTextInputEditText);
            editTextConfirmPass = (EditText) findViewById(R.id.confirmTextInputEditText);





    }

}
