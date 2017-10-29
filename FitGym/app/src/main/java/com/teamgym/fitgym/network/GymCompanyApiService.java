package com.teamgym.fitgym.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.GymCompany;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;

/**
 * Created by Usuario on 22/10/2017.
 */

public class GymCompanyApiService {
    public static GymCompany gymCompany = new GymCompany();

    public static GymCompany getGymCompany (int gymCompanyId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.GYM_COMPANIES)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addPathParameter("id", String.valueOf(gymCompanyId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            gymCompany = GymCompany.from(response.getJSONObject("gymCompany"));
                            action.execute(gymCompany);
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
        return gymCompany;
    }
}
