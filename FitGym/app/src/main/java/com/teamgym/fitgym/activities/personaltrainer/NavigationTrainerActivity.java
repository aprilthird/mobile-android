package com.teamgym.fitgym.activities.personaltrainer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.fragments.personaltrainer.AccountTrainerFragment;
import com.teamgym.fitgym.fragments.personaltrainer.EvaluationsFragment;
import com.teamgym.fitgym.fragments.personaltrainer.FoodDietFragment;
import com.teamgym.fitgym.fragments.personaltrainer.RoutinesFragment;

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
        navigateAccordingTo(R.id.navigation_trainer);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_food_diet: return new FoodDietFragment();
            case R.id.navigation_routines: return new RoutinesFragment();
            case R.id.navigation_evaluation: return new EvaluationsFragment();
            case R.id.navigation_account: return new AccountTrainerFragment();
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
