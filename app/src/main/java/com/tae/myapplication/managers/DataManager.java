package com.tae.myapplication.managers;

import android.content.Context;

import com.tae.myapplication.Api.Api;
import com.tae.myapplication.App;

public class DataManager {
    private static DataManager INSTANCE = null;

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private Api api;


    public DataManager() {
        mContext = App.getContext();
        mPreferencesManager = new PreferencesManager();
        api = Controller.createService(Api.class);
    }

    public Api getApi() {
        return api;
    }

    public static DataManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    public Context getContext() {
        return mContext;
    }
}
