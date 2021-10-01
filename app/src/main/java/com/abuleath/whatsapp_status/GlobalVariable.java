package com.abuleath.whatsapp_status;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class GlobalVariable extends Application {

    private static GlobalVariable mContext;
    private int globalCatId;
    SharedPreferences sharedpref;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        sharedpref = getSharedPreferences("MyPrefs", this.MODE_PRIVATE);
        mContext = this;
        globalCatId = 1;
    }

    public static GlobalVariable getContext() {
        return mContext;
    }


    public int getglobalCatId() {
        return globalCatId;
    }

    public void setglobalCatId(int globalCatId) {
        this.globalCatId = globalCatId;
    }

    public void setLastStatusId(int lastStatusId) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("lastStatusId", lastStatusId);
        editor.apply();
    }

    public int getLastStatusId() {
        return sharedpref.getInt("lastStatusId" , 718);
    }
}
