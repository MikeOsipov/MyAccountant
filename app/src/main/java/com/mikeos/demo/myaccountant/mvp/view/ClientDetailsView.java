package com.mikeos.demo.myaccountant.mvp.view;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mikeos.demo.myaccountant.model.client.Client;


public interface ClientDetailsView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setClient(Client client);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setPayments(Cursor data);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setPaymentListMode();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setDetailsMode();

    @StateStrategyType(SkipStrategy.class)
    void moveToCall(String number);

}
