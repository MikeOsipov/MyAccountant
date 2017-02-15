package com.mikeos.demo.myaccountant.model.client;

import com.mikeos.demo.myaccountant.utils.CommonHelper;

import java.util.List;

/**
 * Created on 14.02.17.
 */

public class Company extends Client {

    private String name;
    private List<String> phoneList;
    private String vat;
    private String bankName;
    private String bankAccount;

    public Company() {
    }

    public Company(String name, List<String> phoneList, String vat, String bankName, String bankAccount, long totalSum) {
        super(totalSum);
        this.name = name;
        this.phoneList = phoneList;
        this.vat = vat;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        validate();
    }

    private void validate() {
        if (CommonHelper.checkNullAndEmpty(name, vat, bankName, bankAccount, phoneList)) {
            throw new IllegalArgumentException("There is null properties");
        }
        if (phoneList.size() == 0) {
            throw new IllegalArgumentException("Empty phone list");
        }
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubTitle() {
        return phoneList.get(0);
    }
}
