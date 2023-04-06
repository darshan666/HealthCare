package com.example.hc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String Data_login="status_login",Data_As="as";

    private static SharedPreferences getSharedreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setData_As(Context context,String data){
        SharedPreferences.Editor editor = getSharedreference(context).edit();
        editor.putString(Data_As,data);
        editor.apply();
    }

    public static String getData_As(Context context){
        return getSharedreference(context).getString(Data_As,"");
    }

    public static void setData_login(Context context,Boolean status){
        SharedPreferences.Editor editor = getSharedreference(context).edit();
        editor.putBoolean(Data_login,status);
        editor.apply();
    }

    public static boolean getData_login(Context context){
        return getSharedreference(context).getBoolean(Data_login,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedreference(context).edit();
        editor.remove(Data_As);
        editor.remove(Data_login);
        editor.apply();
    }
}
