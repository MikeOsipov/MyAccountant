package com.mikeos.demo.myaccountant.mvp.presenter;

import com.android.annotations.NonNull;
import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbModelPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientEditView;

import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@InjectViewState
public class ClientEditPresenter extends BaseDbModelPresenter<Client, ClientEditView> {

    public ClientEditPresenter(long id) {
        super(id);
    }

    @Override
    protected void onModelPrepared(Client model) {
        getViewState().showModel(model);
    }

    @Override
    public Class<Client> getModelClass() {
        return Client.class;
    }

    public void saveClient(@NonNull Client client) {
        Client model = getModel();
        if (model != null) {
            client.setId(model.getId());
        }
        saveInternal(client,
                o -> getViewState().onSaveSuccess(),
                throwable -> {
                    throwable.printStackTrace();
                    String messageForUser = throwable.getMessage();// TODO: map exception to readable message
                    getViewState().onSaveFailed(messageForUser);
                });
    }

    private void saveInternal(Client client,
                              Action1<Object> onSuccess, Action1<Throwable> onError) {
        getViewState().onSavingBegins();
        Subscription subscription = Single.create(subscriber -> {
            client.putIntoDB();
            subscriber.onSuccess(null);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
        registerSubscription(subscription);
    }
}
