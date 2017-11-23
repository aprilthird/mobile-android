package com.teamgym.fitgym.activities.personaltrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.adapters.personaltrainer.RoutineDetailsAdapter;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityDetail;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.network.ActivityDetailApiService;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.ArrayList;
import java.util.List;

public class RoutineDetailsActivity extends AppCompatActivity {
    RecyclerView routineDetailsRecyclerView;
    RecyclerView.LayoutManager routineDetailsLayoutManager;
    RoutineDetailsAdapter routineDetailsAdapter;
    List<ActivityDetail> routineDetails;
    int activityDetailsOldSize = 0;
    String tkn = "";
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activity = Activity.from(getIntent().getExtras());
        tkn = getIntent().getStringExtra("token");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditRoutineDetailActivity.class);
                intent.putExtra("token", tkn);
                intent.putExtra("gymCompany", activity.getClient().getpTrainer().getGymCompany().toBundle());
                intent.putExtra("activity", activity.toBundle());
                activityDetailsOldSize = routineDetails.size();
                context.startActivity(intent);
            }
        });

        routineDetailsRecyclerView = (RecyclerView) findViewById(R.id.routineDetailsRecyclerView);
        routineDetails = new ArrayList<>();
        routineDetailsAdapter = new RoutineDetailsAdapter(routineDetails);
        routineDetailsAdapter.setTkn(tkn);
        routineDetailsLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        routineDetailsRecyclerView.setLayoutManager(routineDetailsLayoutManager);
        routineDetailsRecyclerView.setAdapter(routineDetailsAdapter);
        updateDetails();
    }

    private void updateDetails() {
        ActivityDetailApiService.getActivitiesByActivityId(tkn, activity, new IActionPostServiceResult<List<ActivityDetail>>() {
            @Override
            public void execute(List<ActivityDetail> activityDetails) {
                routineDetails.clear();
                for (ActivityDetail ad : activityDetails) {
                    ActivityTypeApiService.getActivityType(tkn, ad.getActivityTypeId(), new IActionPostServiceResult<ActivityType>() {
                        @Override
                        public void execute(ActivityType activityType) {
                            activityType.setGymCompany(activity.getClient().getpTrainer().getGymCompany());
                            ad.setActivityType(activityType);
                            routineDetails.add(ad);
                            routineDetailsAdapter.setActivityDetails(routineDetails);
                            routineDetailsAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        routineDetailsAdapter.verifyIfItemChanged(tkn);
        if (routineDetails.size() == 0) return;
        ActivityDetailApiService.getActivitiesByActivityId(tkn, activity, new IActionPostServiceResult<List<ActivityDetail>>() {
            @Override
            public void execute(List<ActivityDetail> activityDetails) {
                if (activityDetailsOldSize != activityDetails.size()) {
                    updateDetails();
                }
            }
        });
    }
}
