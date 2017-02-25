package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbModelPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientDetailsView;


@InjectViewState
public class ClientDetailsPresenter extends BaseDbModelPresenter<Client, ClientDetailsView> {

    private boolean isListMode;

    public ClientDetailsPresenter(long id) {
        super(id);
    }

    @Override
    protected void onModelPrepared(Client model) {
        getViewState().setClient(model);
        listMode();
    }

    private void listMode() {
        getViewState().setPaymentListMode();
        isListMode = true;
    }

    private void detailsMode() {
        getViewState().setDetailsMode();
        isListMode = false;
    }

    public void toggleState(){
        if (isListMode) {
            detailsMode();
        } else {
            listMode();
        }
    }

    @Override
    public Class<Client> getModelClass() {
        return Client.class;
    }
}
