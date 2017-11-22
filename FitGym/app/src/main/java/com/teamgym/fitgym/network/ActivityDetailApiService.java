package com.teamgym.fitgym.network;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Activity;
import com.teamgym.fitgym.models.ActivityDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 22/11/2017.
 */

public class ActivityDetailApiService {
    public static List<ActivityDetail> activityDetails = new ArrayList<>();
    public static ActivityDetail activityDetail = new ActivityDetail();

    public static void getActivityDetails(String tkn, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_DETAILS)
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
                            activityDetails = ActivityDetail.from(response.getJSONArray("activityDetails"));
                            action.execute(activityDetails);
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

    public static void getActivitiesByActivityId(String tkn, int activityId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_DETAILS)
                .addQueryParameter("activityId", String.valueOf(activityId))
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
                            activityDetails = ActivityDetail.from(response.getJSONArray("activityDetails"));
                            action.execute(activityDetails);
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

    public static void getActivitiesByActivityId(String tkn, Activity activity, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_DETAILS)
                .addQueryParameter("activityId", String.valueOf(activity.getId()))
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
                            activityDetails = ActivityDetail.from(response.getJSONArray("activityDetails"), activity);
                            action.execute(activityDetails);
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

    public static void getActivityDetail(String tkn, int activityDetailId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ACTIVITY_DETAILS + "/{id}")
                .addPathParameter("id", String.valueOf(activityDetailId))
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
                            activityDetail = ActivityDetail.from(response.getJSONObject("activityDetail"));
                            action.execute(activityDetail);
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

    public static void createActivityDetail(String tkn, ActivityDetail pActivityDetail, IActionPostServiceResult action) {
        AndroidNetworking.post(FitGymApiService.ACTIVITY_DETAILS)
                .addJSONObjectBody(pActivityDetail.toJSONObject())
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
                            activityDetail = ActivityDetail.from(response.getJSONObject("activityDetail"));
                            action.execute(activityDetail);
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

    public static void updateActivityDetail(String tkn, ActivityDetail pActivityDetail, IActionPostServiceResult action) {
        AndroidNetworking.put(FitGymApiService.ACTIVITY_DETAILS + "/{id}")
                .addPathParameter("id", String.valueOf(pActivityDetail.getId()))
                .addJSONObjectBody(pActivityDetail.toJSONObject())
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
                            activityDetail = ActivityDetail.from(response.getJSONObject("activityDetail"));
                            action.execute(activityDetail);
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
