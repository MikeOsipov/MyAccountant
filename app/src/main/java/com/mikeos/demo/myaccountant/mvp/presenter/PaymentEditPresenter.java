package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.Repository;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.PaymentEditView;

import rx.Observable;

@InjectViewState
public class PaymentEditPresenter extends BaseEditPresenter<Payment, PaymentEditView> {

    private long userId;
    private long oldSum;

    public PaymentEditPresenter(long id, long userId) {
        super(id);
        this.userId = userId;
    }

    @Override
    protected void onModelPrepared(Payment model) {
        super.onModelPrepared(model);
        oldSum = model == null ? 0 : model.getSum();
    }

    @Override
    protected Payment transformBeforeSaving(Payment item) {
        if (item.getUserId() < 0) {
            item.setUserId(userId);
        }
        return super.transformBeforeSaving(item);
    }

    @Override
    protected Observable.Transformer<Payment, Payment> getObservableTransformer() {
        return paymentObservable -> paymentObservable.map(payment -> {
            Client client = Client.byId(payment.getUserId(), Client.class);
            client.setTotalSum(client.getTotalSum() - oldSum + payment.getSum());
            new ClientRepository().update(client).subscribe(c -> {});
            return payment;
        });
    }

    @Override
    protected Repository<Payment> getRepository() {
        return new PaymentRepository();
    }

    //    @Override
//    protected Subscription getSaveSubscription(Payment update, Action1<Object> onSuccess, Action1<Throwable> onError) {
//        return Single.create(subscriber -> {
//            if(update.getUserId() < 0){
//                update.setUserId(userId);
//            }
//
//            update.putIntoDB();
//
//            Client client = Client.byId(update.getUserId(), Client.class);
//            client.setTotalSum(client.getTotalSum() - oldSum + update.getSum());
//            client.putIntoDB();
//
//            subscriber.onSuccess(null);
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(onSuccess, onError);
//    }

    @Override
    public Class<Payment> getModelClass() {
        return Payment.class;
    }
}
