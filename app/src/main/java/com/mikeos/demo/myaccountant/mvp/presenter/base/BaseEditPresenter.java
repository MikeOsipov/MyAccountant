package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.mvp.view.base.BaseEditView;
import com.mikeos.demo.myaccountant.utils.RxHelper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

        transformBeforeSaving(update);

        Repository<T> repository = getRepository();
        Observable<T> observable = update.isValidId() ? repository.update(update) : repository.create(update);

        registerSubscription(observable
                .compose(RxHelper.asyncTransformer())
                .subscribe(onSuccess, onError));
    }

    protected abstract Repository<T> getRepository();

    protected void transformBeforeSaving(T item) {
    }
}
