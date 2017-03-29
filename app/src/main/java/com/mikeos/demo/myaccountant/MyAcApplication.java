package com.mikeos.demo.myaccountant;

import android.app.Application;
import android.content.Context;

import com.mikeos.demo.myaccountant.di.DaggerMainComponent;
import com.mikeos.demo.myaccountant.di.MainComponent;
import com.mikeos.demo.myaccountant.di.MainModule;
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
    private static MainComponent component;

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


        component = DaggerMainComponent.builder().mainModule(new MainModule()).build();
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

    public static MainComponent getComponent() {
        return component;
    }
}
