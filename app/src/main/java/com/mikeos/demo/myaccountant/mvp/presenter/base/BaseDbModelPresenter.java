package com.mikeos.demo.myaccountant.mvp.presenter.base;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.model.DbModel;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public abstract class BaseDbModelPresenter<M extends DbModel<M>, V extends MvpView> extends CursorTypedPresenter<M, V> {

    private long id;
    private M model;

    public M getModel() {
        return model;
    }

    protected abstract void onModelPrepared(M model);

    public BaseDbModelPresenter(long id) {
        this.id = id;
    }

    protected void load() {
        if (id < 0) {
            setModel(null);
        } else {
            Subscription subscription = getRepository()
                    .query(id, getModelClass())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::setModel);
            registerSubscription(subscription);
        }
    }

    private void setModel(M model) {
        this.model = model;
        onModelPrepared(model);
    }
}
