package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.PaymentEditView;

import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@InjectViewState
public class PaymentEditPresenter extends BaseEditPresenter<Payment, PaymentEditView> {

    private long userId;

    public PaymentEditPresenter(long id, long userId) {
        super(id);
        this.userId = userId;
    }

    @Override
    protected Subscription getSaveSubscription(Payment update, Action1<Object> onSuccess, Action1<Throwable> onError) {
        return Single.create(subscriber -> {
            if(update.getUserId() < 0){
                update.setUserId(userId);
            }

            update.putIntoDB();
            subscriber.onSuccess(null);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }

    @Override
    public Class<Payment> getModelClass() {
        return Payment.class;
    }
}
