package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbModelPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientDetailsView;

import javax.inject.Inject;


@InjectViewState
public class ClientDetailsPresenter extends BaseDbModelPresenter<Client, ClientDetailsView> {

    @Inject
    ClientRepository repository;
    private boolean isListMode;

    public ClientDetailsPresenter(long id) {
        super(id);
        MyAcApplication.getComponent().inject(this);
        load();
        listMode();
    }

    @Override
    protected void onModelPrepared(Client model) {
        getViewState().setClient(model);
    }

    private void listMode() {
        isListMode = true;
        getViewState().setPaymentListMode();
    }

    private void detailsMode() {
        isListMode = false;
        getViewState().setDetailsMode();
    }

    public boolean isListMode() {
        return isListMode;
    }

    public void toggleState() {
        if (isListMode) {
            detailsMode();
        } else {
            listMode();
        }
    }

    public void handlePhoneTap(String phone) {
        getViewState().moveToCall(phone);
    }

    public void handleEdit() {
        getViewState().moveToEdit(getModel().getId());
    }

    @Override
    protected Repository<Client> getRepository() {
        return repository;
    }

    @Override
    protected Class<Client> getModelClass() {
        return Client.class;
    }
}
