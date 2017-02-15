package com.mikeos.demo.myaccountant;

import android.app.Application;
import android.content.Context;

import com.squareup.sqlbrite.BriteContentResolver;
import com.squareup.sqlbrite.SqlBrite;

import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.ProviderCompartment;
import rx.schedulers.Schedulers;

/**
 * Created on 14.02.17.
 */

public class MyAcApplication extends Application {

    private static MyAcApplication application;

    public static MyAcApplication getInstance() {
        return application;
    }

    private BriteContentResolver briteProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        briteProvider = sqlBrite.wrapContentProvider(getContentResolver(), Schedulers.io());
    }

    public static Context getAppContext() {
        return getInstance();
    }

    public static BriteContentResolver getBriteProvider() {
        return getInstance().briteProvider;
    }

    public static ProviderCompartment cupboard() {
        return CupboardFactory.cupboard().withContext(getAppContext());
    }
}
