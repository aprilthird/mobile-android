package com.teamgym.fitgym.activities.gymclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.fragments.client.AccountClientFragment;
import com.teamgym.fitgym.fragments.client.EvaluationsClientFragment;
import com.teamgym.fitgym.fragments.client.FoodDietClientFragment;
import com.teamgym.fitgym.fragments.client.RoutinesClientFragment;
import com.teamgym.fitgym.fragments.personaltrainer.AccountTrainerFragment;
import com.teamgym.fitgym.fragments.personaltrainer.EvaluationsFragment;
import com.teamgym.fitgym.fragments.personaltrainer.FoodDietFragment;
import com.teamgym.fitgym.fragments.personaltrainer.RoutinesFragment;

public class NavigationClientActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_navigation_client);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_client);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTo(R.id.navigation_client);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_food_diet: return new FoodDietClientFragment();
            case R.id.navigation_routines: return new RoutinesClientFragment();
            case R.id.navigation_evaluation: return new EvaluationsClientFragment();
            case R.id.navigation_account: return new AccountClientFragment();
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
