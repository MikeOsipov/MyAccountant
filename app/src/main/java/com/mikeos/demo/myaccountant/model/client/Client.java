package com.mikeos.demo.myaccountant.model.client;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.utils.CommonHelper;

import java.util.List;

/**
 * Created on 14.02.17.
 */

public class Client extends DbModel<Client> {

    public static final int PHONE_LIST_LIMIT = 6;

    private long totalSum;
    private String name;
    private List<String> phoneList;
    private String vat;
    private String bankName;
    private String bankAccount;

    public Client() {
    }

    public Client(String name, List<String> phoneList, String vat, String bankName, String bankAccount, long totalSum) {
        this.totalSum = totalSum;
        this.name = name;
        this.phoneList = phoneList;
        this.vat = vat;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        validate();
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public String getVat() {
        return vat;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getTotalSumFormat() {
        return MyAcApplication.getAppContext().getString(R.string.money_format, totalSum);
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
    protected Class<Client> getEntityClass() {
        return Client.class;
    }
}
