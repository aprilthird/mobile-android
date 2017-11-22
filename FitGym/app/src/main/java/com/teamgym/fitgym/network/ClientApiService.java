package com.teamgym.fitgym.network;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Client;
import com.teamgym.fitgym.models.PTrainer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 22/10/2017.
 */

public class ClientApiService {
    public static List<Client> clients = new ArrayList<>();
    public static Client client = new Client();

    public static List<Client> getClients(String tkn, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"));
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static List<Client> getClientsByGymId (String tkn, int gymCompanyId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"));
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static List<Client> getClientsByTrainerId(String tkn, int personalTrainerId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .addQueryParameter("personalTrainerId", String.valueOf(personalTrainerId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"));
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static List<Client> getClientsByTrainerId(String tkn, final PTrainer trainer, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .addQueryParameter("personalTrainerId", String.valueOf(trainer.getId()))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"), trainer);
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static List<Client> getClientsByGymId (String tkn, int gymCompanyId, String query, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .addQueryParameter("query", query)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"));
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static List<Client> getClientsByTrainerId(String tkn, int personalTrainerId, String query, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addQueryParameter("personalTrainerId", String.valueOf(personalTrainerId))
                .addQueryParameter("query", query)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            clients = Client.from(response.getJSONArray("clients"));
                            action.execute(clients);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return clients;
    }

    public static Client getClient(String tkn, final int clientId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.CLIENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addPathParameter("id", String.valueOf(clientId))
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase("status")) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            client = Client.from(response.getJSONObject("client"));
                            action.execute(client);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
        return client;
    }
}
