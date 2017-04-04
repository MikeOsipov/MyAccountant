package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.MyAcApplication;

import javax.inject.Inject;

/**
 * Created on 04.04.17.
 */

public class RepositoryHolder {

    private static RepositoryHolder instance;

    public static RepositoryHolder getInstance() {
        if(instance == null){
            instance = new RepositoryHolder();
        }
        return instance;
    }

    @Inject
    PaymentRepository paymentRepository;
    @Inject
    ClientRepository clientRepository;

    public RepositoryHolder() {
        MyAcApplication.getComponent().inject(this);
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public PaymentRepository getPaymentRepository() {
        return paymentRepository;
    }
}
