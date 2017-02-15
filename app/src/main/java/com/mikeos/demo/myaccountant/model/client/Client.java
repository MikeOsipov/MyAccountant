package com.mikeos.demo.myaccountant.model.client;

import com.mikeos.demo.myaccountant.model.DbModel;

/**
 * Created on 14.02.17.
 */

public abstract class Client extends DbModel<Client> implements IClientItem {

    private long totalSum;

    public Client() {
    }

    public Client(long totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }


}
