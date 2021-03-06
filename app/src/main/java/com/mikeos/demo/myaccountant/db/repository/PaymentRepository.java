package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.api.ApiRequester;
import com.mikeos.demo.myaccountant.db.repository.base.RestRepository;
import com.mikeos.demo.myaccountant.model.Client;
import com.mikeos.demo.myaccountant.model.Payment;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action3;

/**
 * Created on 28.03.17.
 */

public class PaymentRepository extends RestRepository<Payment> {

    public PaymentRepository() {
        MyAcApplication.getComponent().inject(this);
    }

    @Override
    public Observable<Payment> create(Payment item) {
        return withUpdateClient(item, super.create(item), (paymentOld, paymentCreated, client) -> {
            client.setTotalSum(client.getTotalSum() + paymentCreated.getSum());
        });
    }

    @Override
    public Observable<Payment> update(Payment item) {
        return withUpdateClient(item, super.create(item),
                (paymentOld, paymentUpdated, client) -> {
                    client.setTotalSum(client.getTotalSum() - paymentOld.getSum() + paymentUpdated.getSum());
                });
    }

    @Override
    public Observable<Payment> remove(Payment item) {
        return withUpdateClient(item, super.remove(item), (payment, paymentRemoved, client) -> {
            client.setTotalSum(client.getTotalSum() - paymentRemoved.getSum());
        });
    }

    private Observable<Payment> withUpdateClient(Payment item, Observable<Payment> observable,
                                                 Action3<Payment, Payment, Client> editor) {
        Observable<Payment> getOldObservable =
                item.isValidId() ? get(item.getId(), Payment.class) : Observable.create(subscriber -> {
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                });
        ClientRepository clientRepository = RepositoryHolder.getInstance().getClientRepository();
        return Observable.combineLatest(
                getOldObservable,
                observable,
                clientRepository.get(item.getUserId(), Client.class),
                (paymentOld, paymentUpdated, client) -> {
                    if (client != null) {
                        editor.call(paymentOld, paymentUpdated, client);
                        clientRepository.update(client).subscribe();
                    }
                    return paymentUpdated;
                });
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
