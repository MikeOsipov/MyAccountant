package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.model.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbModelPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientDetailsView;


@InjectViewState
public class ClientDetailsPresenter extends BaseDbModelPresenter<Client, ClientDetailsView> {

    private boolean isListMode;

    public ClientDetailsPresenter(long id) {
        super(id);
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

    public void handleEdit(){
        getViewState().moveToEdit(getModel().getId());
    }

    @Override
    public Class<Client> getModelClass() {
        return Client.class;
    }
}
