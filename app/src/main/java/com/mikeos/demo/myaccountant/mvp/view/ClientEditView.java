package com.mikeos.demo.myaccountant.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.model.client.Client;

public interface ClientEditView extends MvpView {

    void showModel(Client client);

    void onSavingBegins();

    void onSaveSuccess();

    void onSaveFailed(String message);

}
