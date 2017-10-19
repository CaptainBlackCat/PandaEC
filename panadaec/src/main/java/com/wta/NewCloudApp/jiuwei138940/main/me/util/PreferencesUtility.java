package com.wta.NewCloudApp.jiuwei138940.main.me.util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Lenovo on 2017/8/1.
 */

public class PreferencesUtility {
    private static volatile SharedPreferences mPreferences;
    private static PreferencesUtility sInstance;
    public PreferencesUtility(final Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferencesUtility getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (PreferencesUtility.class) {
                if (sInstance == null) {
                    sInstance = new PreferencesUtility(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
    public void setOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public boolean isFirst(){
        return mPreferences.getBoolean("isfirst",true);
    }

    public void setIsFirst(boolean isFirst){
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("isfirst", isFirst);
        editor.apply();
    }

    public void setSelectAddress(int id){
        final SharedPreferences.Editor editor=mPreferences.edit();
        editor.putInt("currentAddressId",id);
        editor.apply();

    }

    public int getSelectArress(){
        return mPreferences.getInt("currentAddressId",0);
    }

    public void saveToken(String token){
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putString("token",token);
        edit.commit();
    }

    public String getToken(){
        String token = mPreferences.getString("token", null);
        return token;
    }


    public void saveSession(String s) {
    //session
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putString("session",s);
        edit.commit();
    }

    public String getSession(){
        String token = mPreferences.getString("session", null);
        return token;
    }
}
