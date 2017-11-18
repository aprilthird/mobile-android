package com.teamgym.fitgym.activities.personaltrainer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.fragments.personaltrainer.ActivitiesTrainerFragment;
import com.teamgym.fitgym.fragments.personaltrainer.ClientsTrainerFragment;
import com.teamgym.fitgym.fragments.personaltrainer.FoodDietFragment;
import com.teamgym.fitgym.fragments.personaltrainer.TrainerSettingsFragment;

public class NavigationTrainerActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_trainer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_trainer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon_bar);
        navigateAccordingTo(R.id.navigation_clients);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_clients:
                getSupportActionBar().setSubtitle(getString(R.string.title_trainer_clients));
                return new ClientsTrainerFragment();
            case R.id.navigation_activities:
                getSupportActionBar().setSubtitle(getString(R.string.title_trainer_activities));
                return new ActivitiesTrainerFragment();
            case R.id.navigation_settings:
                getSupportActionBar().setSubtitle(getString(R.string.title_trainer_settings));
                return new TrainerSettingsFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragmentFor(id))
                    .commit();
            return true;
        } catch (NullPointerException e) {
            return false;       }
    }

}
