package com.teamgym.fitgym.fragments.client;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymclient.BodyMeasurementActivity;
import com.teamgym.fitgym.activities.gymclient.CredentialsActivity;
import com.teamgym.fitgym.activities.personaltrainer.AddFoodActivity;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.network.ClientApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountClientFragment extends Fragment {
    TextView nameTextView;
    TextView lastnameTextView;
    TextView emailTextView;
    TextView addressTextView;
    TextView phoneTextView;
    TextView objTextView;
    Button credentialsButton;
    Button measuresButton;

    Client client;

    public AccountClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account_client, container, false);

        nameTextView= (TextView) view.findViewById(R.id.nameTextView);
        lastnameTextView= (TextView) view.findViewById(R.id.lastnameTextView);
        emailTextView= (TextView) view.findViewById(R.id.emailTextView);
        addressTextView= (TextView) view.findViewById(R.id.addressTextView);
        objTextView= (TextView) view.findViewById(R.id.objTextView);
        credentialsButton= (Button) view.findViewById(R.id.credentialsButton);
        measuresButton= (Button) view.findViewById(R.id.measureButton);


        nameTextView.setText(client.getFirstName());
        lastnameTextView.setText(client.getLastName());
        emailTextView.setText(client.getEmail());
        addressTextView.setText(client.getAddress());
        measuresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CredentialsActivity.class);
                startActivity(intent);

            }
        });

        credentialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BodyMeasurementActivity.class);
                startActivity(intent);
            }
        });



        return view;


    }

}
