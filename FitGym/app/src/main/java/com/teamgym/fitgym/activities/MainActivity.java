package com.teamgym.fitgym.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.solver.SolverVariable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.teamgym.fitgym.R;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText(R.string.welcome_message);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_NAME = "section_name";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, String sectionName) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_SECTION_NAME, sectionName);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            final TextView loginTextView = (TextView) rootView.findViewById(R.id.loginTextView);

            SpannableStringBuilder str = new SpannableStringBuilder(
                    getString(R.string.role_name, getArguments().getString(ARG_SECTION_NAME)));
            str.setSpan(new StyleSpan(Typeface.BOLD), 4, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            loginTextView.setText(str);

            // EXPL Set Text Style depending on MotionEvent
            loginTextView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            String text = loginTextView.getText().toString();
                            SpannableString content = new SpannableString(text);
                            content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
                            content.setSpan(new StyleSpan(Typeface.BOLD), 4, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            loginTextView.setText(content);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            SpannableStringBuilder str = new SpannableStringBuilder(
                                    getString(R.string.role_name, getArguments().getString(ARG_SECTION_NAME)));
                            str.setSpan(new StyleSpan(Typeface.BOLD), 4, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            loginTextView.setText(str);
                            break;
                    }
                    return false;
                }
            });

            // EXPL Call login activity depending on its role
            loginTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    callLoginActivityFor(getArguments().getString(ARG_SECTION_NAME), view);
                }
            });

            return rootView;
        }

        private void callLoginActivityFor(String sectionName, View view) {
            switch (sectionName) {
                case "Client":
                    // TODO Start Login Activity for Client
                    // startActivity(new Intent(view.getContext(), LoginClientActivity.class));
                    break;
                case "Personal Trainer":
                    // TODO Start Login Activity for Personal Trainer

                    break;
                case "Gym Company":
                    startActivity(new Intent(view.getContext(), LoginGymCompanyActivity.class));
                    break;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, getPageTitle(position).toString());
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Client";
                case 1:
                    return "Personal Trainer";
                case 2:
                    return "Gym Company";
            }
            return null;
        }
    }
}
