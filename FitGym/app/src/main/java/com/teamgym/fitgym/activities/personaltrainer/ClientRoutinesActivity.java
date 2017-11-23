package com.teamgym.fitgym.activities.personaltrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.personaltrainer.RoutinesAdapter;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.ActivityApiService;
import com.teamgym.fitgym.network.EstablishmentApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.ArrayList;
import java.util.List;

public class ClientRoutinesActivity extends AppCompatActivity {
    RecyclerView routinesRecyclerView;
    RoutinesAdapter routinesAdapter;
    RecyclerView.LayoutManager routinesLayoutManager;
    List<Activity> routines;
    int routinesOldSize = 0;
    String tkn = "";
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_routines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        client = Client.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addRoutineButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditRoutineActivity.class);
                intent.putExtra("token", tkn);
                intent.putExtra("gymCompany", client.getpTrainer().getGymCompany().toBundle());
                intent.putExtra("client", client.toBundle());
                context.startActivity(intent);
            }
        });

        routinesRecyclerView = (RecyclerView) findViewById(R.id.routinesRecyclerView);
        routines = new ArrayList<>();
        routinesAdapter = new RoutinesAdapter(routines);
        routinesAdapter.setTkn(tkn);
        routinesLayoutManager = new GridLayoutManager(this, 1);
        routinesRecyclerView.setAdapter(routinesAdapter);
        routinesRecyclerView.setLayoutManager(routinesLayoutManager);
        updateRoutines();
    }

    private void updateRoutines() {
        ActivityApiService.getActivitiesByClientId(tkn, client, new IActionPostServiceResult<List<Activity>>() {
            @Override
            public void execute(List<Activity> activities) {
                routines.clear();
                for(Activity a: activities) {
                    EstablishmentApiService.getEstablishment(tkn, a.getEstablishmentId(), new IActionPostServiceResult<Establishment>() {
                        @Override
                        public void execute(Establishment establishment) {
                            establishment.setGymCompany(client.getpTrainer().getGymCompany());
                            a.setEstablishment(establishment);
                            routines.add(a);
                            routinesAdapter.setActivities(routines);
                            routinesAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        routinesAdapter.verifyIfItemChanged(tkn);
        if (routinesOldSize == 0) return;
        ActivityApiService.getActivitiesByClientId(tkn, client, new IActionPostServiceResult<List<Activity>>() {
            @Override
            public void execute(List<Activity> activities) {
                if (routinesOldSize != activities.size()) {
                    updateRoutines();
                }
            }
        });
    }
}
