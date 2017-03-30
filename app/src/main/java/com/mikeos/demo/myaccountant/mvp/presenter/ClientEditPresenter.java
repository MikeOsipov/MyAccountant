package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.Repository;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientEditView;

import javax.inject.Inject;

@InjectViewState
public class ClientEditPresenter extends BaseEditPresenter<Client, ClientEditView> {

    @Inject
    ClientRepository repository;

    public ClientEditPresenter(long id) {
        super(id);
        MyAcApplication.getComponent().inject(this);
    }

    @Override
    protected Repository<Client> getRepository() {
        return repository;
    }

    @Override
    public Class<Client> getModelClass() {
        return Client.class;
    }
}
