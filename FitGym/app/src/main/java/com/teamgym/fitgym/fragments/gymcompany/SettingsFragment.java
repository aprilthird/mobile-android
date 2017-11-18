package com.teamgym.fitgym.fragments.gymcompany;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.widget.ANImageView;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.GymCompany;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    GymCompany gymCompany;
    ANImageView gymLogoANIImageView;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());
        gymLogoANIImageView = (ANImageView) view.findViewById(R.id.gymLogoANIImageView);
        gymLogoANIImageView.setErrorImageResId(R.mipmap.ic_launcher);
        gymLogoANIImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        gymLogoANIImageView.setImageUrl(gymCompany.getLogoUrl());
        return view;
    }

}
