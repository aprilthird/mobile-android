package com.teamgym.fitgym.network;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.GymCompany;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 14/11/2017.
 */

public class ActivityTypeApiService {
    public static List<ActivityType> activityTypes = new ArrayList<>();
    public static ActivityType activityType = new ActivityType();

    public static void getActivityTypes(IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_TYPES)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityTypes = ActivityType.from(response.getJSONArray("activityTypes"));
                            action.execute(activityTypes);
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

    public static void getActivityTypesByGymId(int gymCompanyId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_TYPES)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityTypes = ActivityType.from(response.getJSONArray("activityTypes"));
                            action.execute(activityTypes);
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

    public static void getActivityTypesByGymId(GymCompany gymCompany, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_TYPES)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompany.getId()))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityTypes = ActivityType.from(response.getJSONArray("activityTypes"), gymCompany);
                            action.execute(activityTypes);
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

    public static void getActivityType(int activityTypeId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_TYPES + "/{id}")
                .addPathParameter("id", String.valueOf(activityTypeId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityType = ActivityType.from(response.getJSONObject("activityType"));
                            action.execute(activityType);
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

    public static void createActivityType(ActivityType pActivityType, IActionPostServiceResult action) {
        AndroidNetworking.post(FitGymApiService.ACTIVITY_TYPES)
                .addJSONObjectBody(pActivityType.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityType = ActivityType.from(response.getJSONObject("activityType"));
                            action.execute(activityType);
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

    public static void updateActivityType(ActivityType pActivityType, IActionPostServiceResult action) {
        AndroidNetworking.put(FitGymApiService.ACTIVITY_TYPES + "/{id}")
                .addPathParameter("id", String.valueOf(pActivityType.getId()))
                .addJSONObjectBody(pActivityType.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            activityType = ActivityType.from(response.getJSONObject("activityType"));
                            action.execute(activityType);
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
