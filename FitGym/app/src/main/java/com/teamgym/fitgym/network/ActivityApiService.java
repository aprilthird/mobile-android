package com.teamgym.fitgym.network;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 14/11/2017.
 */

public class ActivityApiService {
    public static List<Activity> activities = new ArrayList<>();
    public static Activity activity = new Activity();

    public static void getActivities(String tkn, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITIES)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activities = Activity.from(response.getJSONArray("activities"));
                            action.execute(activities);
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
    }

    public static void getActivitiesByClientId(String tkn, int clientId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITIES)
                .addQueryParameter("clientId", String.valueOf(clientId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activities = Activity.from(response.getJSONArray("activities"));
                            action.execute(activities);
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
    }

    public static void getActivitiesByClientId(String tkn, Client client, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITIES)
                .addQueryParameter("clientId", String.valueOf(client.getId()))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activities = Activity.from(response.getJSONArray("activities"), client);
                            action.execute(activities);
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
    }

    public static void getActivity(String tkn, int ActivityId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITIES + "/{id}")
                .addPathParameter("id", String.valueOf(ActivityId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activity = Activity.from(response.getJSONObject("activity"));
                            action.execute(activity);
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

    }

    public static void createActivity(String tkn, Activity pActivity, IActionPostServiceResult action) {
        AndroidNetworking.post(FitGymApiService.ACTIVITIES)
                .addJSONObjectBody(pActivity.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activity = Activity.from(response.getJSONObject("activity"));
                            action.execute(activity);
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
    }

    public static void updateActivity(String tkn, Activity pActivity, IActionPostServiceResult action) {
        AndroidNetworking.put(FitGymApiService.ACTIVITIES + "/{id}")
                .addPathParameter("id", String.valueOf(pActivity.getId()))
                .addJSONObjectBody(pActivity.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activity = Activity.from(response.getJSONObject("activity"));
                            action.execute(activity);
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
    }
}