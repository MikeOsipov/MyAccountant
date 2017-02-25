package com.mikeos.demo.myaccountant.mvp.view.base;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.model.client.Client;

public interface BaseEditView<T extends DbModel> extends MvpView {

    void showModel(T client);

    void onSavingBegins();

    void onSaveSuccess();

    void onSaveFailed(String message);

}
