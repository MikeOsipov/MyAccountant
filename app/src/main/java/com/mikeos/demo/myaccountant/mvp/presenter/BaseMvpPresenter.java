package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    private CompositeSubscription compositeSubscription;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    protected void registerSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
