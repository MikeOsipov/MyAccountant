package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;

import javax.inject.Inject;

/**
 * Created on 15.02.17.
 */

@InjectViewState
public class ClientListPresenter extends BaseDbListPresenter<Client, DBListView> {

    @Inject
    ClientRepository repository;

    public ClientListPresenter() {
        MyAcApplication.getComponent().inject(this);
        load();
    }

    @Override
    protected Class<Client> getModelClass() {
        return Client.class;
    }

    @Override
    protected Repository<Client> getRepository() {
        return repository;
    }
}
