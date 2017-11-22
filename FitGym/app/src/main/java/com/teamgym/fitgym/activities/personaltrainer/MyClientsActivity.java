package com.teamgym.fitgym.activities.personaltrainer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.gymcompany.ClientsAdapter;
import com.teamgym.fitgym.utilities.DataService;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MyClientsActivity extends AppCompatActivity {

    RecyclerView clientsRecyclerView;
    ClientsAdapter clientsAdapter;
    RecyclerView.LayoutManager clientsLayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clientsRecyclerView = (RecyclerView)findViewById(R.id.clientsRecyclerView);
        //List<Client> clientList = (new DataService().getPTrainerClients());
        clientsAdapter = new ClientsAdapter();
        clientsLayoutManager = new GridLayoutManager(this,getSpanCountFor(getResources().getConfiguration()));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyClientsActivity.this,NewClientActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private int getSpanCountFor(Configuration configuration)
    {
        return configuration.orientation == ORIENTATION_LANDSCAPE ? 3 : 2;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ((GridLayoutManager)clientsLayoutManager).setSpanCount(getSpanCountFor(newConfig));
    }
}
