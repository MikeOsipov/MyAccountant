package com.mikeos.demo.myaccountant.db;

import com.google.gson.Gson;
import com.mikeos.demo.myaccountant.BuildConfig;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.model.Client;

import java.util.Date;

import nl.littlerobots.cupboard.tools.convert.GsonFieldConverter;
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

    private static final int DB_VERSION = 2;

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations()
                .registerFieldConverter(Date.class, new GsonFieldConverter<>(new Gson(), Date.class))
                .registerFieldConverterFactory(new ListFieldConverterFactory(new Gson()))
                .build());

        cupboard().register(Client.class);
        cupboard().register(Payment.class);
    }

    public AppContentProvider() {
        super(AUTHORITY, DB_VERSION);
    }

    public static UriHelper getUriHelper() {
        return UriHelper.with(AUTHORITY);
    }
}
