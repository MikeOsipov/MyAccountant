package com.mikeos.demo.myaccountant.model;


import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.qbusict.cupboard.annotation.Column;

public class Payment extends DbModel<Payment> {

    public static final String COLUMN_USER_ID = "userId";

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

    private long sum;
    @Column(COLUMN_USER_ID)
    private long userId;
    private Date date;

    public Payment() {
    }

    public Payment(long sum, long userId, Date date) {
        this.sum = sum;
        this.userId = userId;
        this.date = date;
    }

    public String getSumFormat() {
        return MyAcApplication.getAppContext().getString(R.string.money_format, sum);
    }

    public String getDateFormat() {
        return date != null ? FORMAT.format(date) : null;
    }

    @Override
    protected Class<Payment> getEntityClass() {
        return Payment.class;
    }
}
