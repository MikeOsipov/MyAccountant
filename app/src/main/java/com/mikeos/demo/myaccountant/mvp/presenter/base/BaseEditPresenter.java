package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.support.annotation.NonNull;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.mvp.view.base.BaseEditView;

import rx.Subscription;
import rx.functions.Action1;

public abstract class BaseEditPresenter<T extends DbModel<T>, V extends BaseEditView<T>> extends BaseDbModelPresenter<T, V> {

    public BaseEditPresenter(long id) {
        super(id);
    }

    @Override
    protected void onModelPrepared(T model) {
        getViewState().showModel(model);
    }

    public void saveModel(@NonNull T update) {
        T model = getModel();
        if (model != null) {
            update.setId(model.getId());
        }
        saveInternal(update,
                o -> getViewState().onSaveSuccess(),
                throwable -> {
                    throwable.printStackTrace();
                    String messageForUser = throwable.getMessage();// TODO: map exception to readable message
                    getViewState().onSaveFailed(messageForUser);
                });
    }

    private void saveInternal(T update, Action1<Object> onSuccess, Action1<Throwable> onError) {
        getViewState().onSavingBegins();
        Subscription subscription = getSaveSubscription(update, onSuccess, onError);
        registerSubscription(subscription);
    }

    protected abstract Subscription getSaveSubscription(T update, Action1<Object> onSuccess, Action1<Throwable> onError);
}
