package com.teamgym.fitgym.fragments.personaltrainer;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.teamgym.fitgym.R;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesTrainerFragment extends Fragment implements MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EmptyViewClickListener, WeekView.EventLongPressListener {
    WeekView mWeekView;

    public ActivitiesTrainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities_trainer, container, false);

        mWeekView = (WeekView) view.findViewById(R.id.weekView);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setEmptyViewClickListener(this);

        return view;
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();
        ArrayList<WeekViewEvent> newEvents = getNewEvents(newYear, newMonth);
        events.addAll(newEvents);
        return events;
    }

    private ArrayList<WeekViewEvent> getNewEvents(int year,int month){
        ArrayList<WeekViewEvent> events = new ArrayList<>();
        WeekViewEvent event = new WeekViewEvent();
        Calendar now = Calendar.getInstance();
        Calendar then = Calendar.getInstance();
        event.setStartTime(now);
        event.setEndTime(then);
        events.add(event);
        return events;
    }


    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEmptyViewClicked(Calendar time) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
}
