package com.mikeos.demo.myaccountant.db.specs;

import com.mikeos.demo.myaccountant.model.Payment;

import nl.qbusict.cupboard.ProviderCompartment;

/**
 * Created on 04.04.17.
 */

public class ByClientIdSpec implements RepositorySpecification<Payment> {

    private long clientId;

    public ByClientIdSpec(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public ProviderCompartment.QueryBuilder<Payment> transformQuery(ProviderCompartment.QueryBuilder<Payment> builder) {
        return builder.withSelection(Payment.COLUMN_USER_ID + "=?", clientId + "");
    }
}
