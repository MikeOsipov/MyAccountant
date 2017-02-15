package com.mikeos.demo.myaccountant;

import android.app.Application;
import android.content.Context;

import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.ProviderCompartment;

/**
 * Created on 14.02.17.
 */

public class MyAcApplication extends Application {

    private static MyAcApplication application;

    public static MyAcApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getAppContext() {
        return getInstance();
    }

    public static ProviderCompartment cupboard() {
        return CupboardFactory.cupboard().withContext(getAppContext());
    }
}
