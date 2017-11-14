package com.teamgym.fitgym.fragments.personaltrainer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.personaltrainer.AddFoodActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDietFragment extends Fragment {

    Button buttonBreakfast;
    Button buttonFirstSnack;
    Button buttonLunch;
    Button buttonSecondSnack;
    Button buttonDinner;

    public FoodDietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_diet, container, false);

        buttonBreakfast = (Button) view.findViewById(R.id.buttonBreakfast);
        buttonFirstSnack = (Button)view.findViewById(R.id.buttonFirstSnack);
        buttonLunch = (Button)view.findViewById(R.id.buttonLunch);
        buttonSecondSnack = (Button)view.findViewById(R.id.buttonSecondSnack);
        buttonDinner = (Button)view.findViewById(R.id.buttonDinner);

        buttonBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        buttonFirstSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        buttonLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        buttonSecondSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        buttonDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
