package com.teamgym.fitgym.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymclient.NavigationClientActivity;
import com.teamgym.fitgym.activities.gymcompany.NavigationGymCompanyActivity;
import com.teamgym.fitgym.activities.personaltrainer.NavigationTrainerActivity;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.models.SuscriptionType;
import com.teamgym.fitgym.network.FitGymApiService;
import com.teamgym.fitgym.network.GymCompanyApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextView visitPageTextView;
    AppCompatButton signInButton;
    AppCompatButton signUpButton;
    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    String receivedToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_LoginTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        visitPageTextView = (TextView) findViewById(R.id.visitPageTextView);
        visitPageTextView.setMovementMethod(LinkMovementMethod.getInstance());
        usernameEditText = (TextInputEditText) findViewById(R.id.usernameTextInputEditText);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);

        signInButton = (AppCompatButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean cancel = false;
                receivedToken = "";

                if(username.trim().isEmpty()) {
                    usernameEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                if(password.trim().isEmpty()) {
                    passwordEditText.setError(getString(R.string.form_field_required));
                    cancel = true;
                }
                if(!cancel) {
                    getToken(view, usernameEditText.getText().toString(), passwordEditText.getText().toString());
                }
                // TODO Validar Mensaje de Error de Login
                if(receivedToken.isEmpty())
                    Toast.makeText(view.getContext(), R.string.alert_invalid_login, Toast.LENGTH_SHORT).show();
            }
        });

        signUpButton = (AppCompatButton) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SuscriptionType.class));
            }
        });
    }

    private void getToken(final View view, String username, String password) {
        // GYM COMPANY CHECK LOGIN
        AndroidNetworking.post(FitGymApiService.GYM_COMPANY_ACCOUNTS)
                .setPriority(Priority.LOW)
                .setTag(R.string.app_name)
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(getString(R.string.app_name), response.getString("message"));
                            }
                            else {
                                receivedToken = response.getString("token");
                                GymCompany gymCompany = GymCompany.from(response.getJSONObject("gymCompany"));
                                Context context = view.getContext();
                                Intent intent = new Intent(context, NavigationGymCompanyActivity.class);
                                intent.putExtra("token", receivedToken);
                                intent.putExtras(gymCompany.toBundle());
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });

        // PERSONAL TRAINER CHECK LOGIN
        AndroidNetworking.post(FitGymApiService.TRAINER_ACCOUNTS)
                .setPriority(Priority.LOW)
                .setTag(R.string.app_name)
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(getString(R.string.app_name), response.getString("message"));
                            }
                            else {
                                receivedToken = response.getString("token");
                                PTrainer pTrainer = PTrainer.from(response.getJSONObject("personalTrainer"));
                                int gymCompanyId = response.getJSONObject("personalTrainer").getInt("gymCompanyId");
                                GymCompanyApiService.getGymCompany(receivedToken, gymCompanyId, new IActionPostServiceResult<GymCompany>() {
                                    @Override
                                    public void execute(GymCompany gymCompany) {
                                        pTrainer.setGymCompany(gymCompany);
                                        Context context = view.getContext();
                                        Intent intent = new Intent(context, NavigationTrainerActivity.class);
                                        intent.putExtra("token", receivedToken);
                                        intent.putExtras(pTrainer.toBundle());
                                        startActivity(intent);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });

        // CLIENT CHECK LOGIN
        AndroidNetworking.post(FitGymApiService.CLIENT_ACCOUNTS)
                .setPriority(Priority.LOW)
                .setTag(R.string.app_name)
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(getString(R.string.app_name), response.getString("message"));
                            }
                            else {
                                receivedToken = response.getString("token");
                                Client client = Client.from(response.getJSONObject("client"));
                                startActivity((new Intent(view.getContext(), NavigationClientActivity.class)).putExtras(client.toBundle()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }
}
