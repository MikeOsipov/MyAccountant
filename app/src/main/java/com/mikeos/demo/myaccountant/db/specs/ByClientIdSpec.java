package com.mikeos.demo.myaccountant.db.specs;

import com.mikeos.demo.myaccountant.model.Payment;

/**
 * Created on 04.04.17.
 */

public class ByClientIdSpec extends BaseSpecification<Payment> {

    public ByClientIdSpec(long clientId) {
        super(Payment.COLUMN_USER_ID + "=?", new String[]{clientId + ""}, null);
    }
}
