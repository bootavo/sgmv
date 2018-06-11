package com.app.sgmv.sgmv.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.sgmv.sgmv.entities.LoginResponse;
import com.app.sgmv.sgmv.entities.Modules;
import com.app.sgmv.sgmv.entities.User;
import com.google.gson.Gson;

public class PreferencesHelper {

    private static String MY_USER_PREF = "user";
    private static Gson gson = new Gson();

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
    }

    public static LoginResponse getMyUserPref(Context context) {
        return gson.fromJson(getPrefs(context).getString(MY_USER_PREF, ""), LoginResponse.class);
    }

    public static void setMyUserPref(Context context, LoginResponse loginResponse) {
        getPrefs(context).edit().putString(MY_USER_PREF, gson.toJson(loginResponse)).commit();
    }

    public static String getMarkStatus(Context context) {
        return getPrefs(context).getString("Estado", null);
    }

    public static void setMarkStatus(Context context, String status) {
        getPrefs(context).edit().putString("Estado",status).commit();
    }

    public static void cleanMyUserPref(Context context){
        getPrefs(context).edit().putString(MY_USER_PREF,null).commit();
    }



}
