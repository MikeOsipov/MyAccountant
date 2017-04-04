package com.mikeos.demo.myaccountant.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.model.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientListView;

import javax.inject.Inject;

/**
 * Created on 15.02.17.
 */

@InjectViewState
public class ClientListPresenter extends BaseDbListPresenter<ClientListView> {

    @Inject
    ClientRepository repository;

    public ClientListPresenter() {
        MyAcApplication.getComponent().inject(this);
    }

    @Override
    protected Uri getDataUri() {
        return AppContentProvider.getUriHelper().getUri(Client.class);
    }

    public void remove(long id) {
        repository.get(id, Client.class)
                .switchMap(client -> repository.remove(client))
                .subscribe(client -> {
                }, throwable -> getViewState().deleteFailed(throwable.getMessage()));
    }

}
