package com.teamgym.fitgym.network;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.Establishment;
import com.teamgym.fitgym.models.GymCompany;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 11/11/2017.
 */

public class EstablishmentApiService {
    public static List<Establishment> establishments = new ArrayList<>();
    public static Establishment establishment = new Establishment();

    public static void getEstablishments (String tkn, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ESTABLISHMENTS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishments = Establishment.from(response.getJSONArray("establishments"));
                            action.execute(establishment);
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

    public static void getEstablishmentsByGymId (String tkn, int gymCompanyId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ESTABLISHMENTS)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishments = Establishment.from(response.getJSONArray("establishments"));
                            action.execute(establishment);
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

    public static void getEstablishmentsByGymId (String tkn, GymCompany gymCompany, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ESTABLISHMENTS)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompany.getId()))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishments = Establishment.from(response.getJSONArray("establishments"), gymCompany);
                            action.execute(establishments);
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

    public static void getEstablishment (String tkn, int establishmentId, IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.ESTABLISHMENTS + "/{id}")
                .addPathParameter("id", String.valueOf(establishmentId))
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishment = Establishment.from(response.getJSONObject("establishment"));
                            action.execute(establishment);
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

    public static void createEstablishment (String tkn, Establishment pEstablishment, IActionPostServiceResult action) {
        AndroidNetworking.post(FitGymApiService.ESTABLISHMENTS)
                .addJSONObjectBody(pEstablishment.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishment = Establishment.from(response.getJSONObject("establishment"));
                            action.execute(establishment);
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

    public static void updateEstablishment (String tkn, Establishment pEstablishment, IActionPostServiceResult action) {
        AndroidNetworking.put(FitGymApiService.ESTABLISHMENTS + "/{id}")
                .addPathParameter("id", String.valueOf(pEstablishment.getId()))
                .addJSONObjectBody(pEstablishment.toJSONObject())
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Basic " + tkn)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            establishment = Establishment.from(response.getJSONObject("establishment"));
                            action.execute(establishment);
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
