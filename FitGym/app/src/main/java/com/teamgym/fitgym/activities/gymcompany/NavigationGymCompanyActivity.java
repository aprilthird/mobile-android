package com.teamgym.fitgym.activities.gymcompany;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.fragments.gymcompany.CompanyFragment;
import com.teamgym.fitgym.fragments.gymcompany.PersonalFragment;
import com.teamgym.fitgym.fragments.gymcompany.SettingsFragment;

public class NavigationGymCompanyActivity extends AppCompatActivity {
    Toolbar toolbar;

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
        setContentView(R.layout.activity_navigation_gym_company);
        getString(R.string.trained_by_trainer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_gym_company);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon_bar);
        navigateAccordingTo(R.id.navigation_personal);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_personal:
                getSupportActionBar().setSubtitle(R.string.title_gym_company_personal);
                return new PersonalFragment();
            case R.id.navigation_company:
                getSupportActionBar().setSubtitle(R.string.title_gym_company_company);
                return new CompanyFragment();
            case R.id.navigation_account:
                getSupportActionBar().setSubtitle(R.string.title_gym_company_settings);
                return new SettingsFragment();
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
        }
        catch (NullPointerException e) {
            return false;
        }
    }
}
