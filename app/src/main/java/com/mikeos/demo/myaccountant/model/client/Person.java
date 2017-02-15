package com.mikeos.demo.myaccountant.model.client;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.R;

/**
 * Created on 14.02.17.
 */

public class Person extends Client {

    private String firstName;
    private String lastName;
    private String phone;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, long totalSum) {
        super(totalSum);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String getTitle() {
        return MyAcApplication.getAppContext().getString(R.string.client_title_format, firstName, lastName);
    }

    @Override
    public String getSubTitle() {
        return phone;
    }
}
