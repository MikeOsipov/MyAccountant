package com.mikeos.demo.myaccountant.db;

import com.google.gson.Gson;
import com.mikeos.demo.myaccountant.BuildConfig;
import com.mikeos.demo.myaccountant.model.client.Client;

import nl.littlerobots.cupboard.tools.convert.ListFieldConverterFactory;
import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created on 14.02.17.
 */

public class AppContentProvider extends CupboardContentProvider {

    private static final int DB_VERSION = 1;

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations()
                .registerFieldConverterFactory(new ListFieldConverterFactory(new Gson()))
                .build());

        cupboard().register(Client.class);
    }

    public AppContentProvider() {
        super(AUTHORITY, DB_VERSION);
    }

    public static UriHelper getUriHelper() {
        return UriHelper.with(AUTHORITY);
    }
}
