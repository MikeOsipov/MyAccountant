package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.api.ApiRequester;
import com.mikeos.demo.myaccountant.model.Payment;

/**
 * Created on 28.03.17.
 */

public class PaymentRepository extends RestRepository<Payment> {

    public PaymentRepository() {
        MyAcApplication.getComponent().inject(this);

    }

    @Override
    public void restCreate(Payment item, ApiRequester requester) {
        requester.addPayment(item);
    }

    @Override
    public void restUpdate(Payment item, ApiRequester requester) {
        requester.updatePayment(item);
    }

    @Override
    public void restDelete(Payment item, ApiRequester requester) {
        requester.deletePayment(item);
    }
}
