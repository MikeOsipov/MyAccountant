package com.mikeos.demo.myaccountant.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;


@InjectViewState
public class PaymentListPresenter extends BaseDbListPresenter<DBListView> {

    private Long userId;

    public PaymentListPresenter(long userId) {
        this.userId = userId;
        load(getDataUri());
    }

    @Override
    protected Uri getDataUri() {
        return userId == null ? null : AppContentProvider.getUriHelper().getUri(Payment.class);
    }

    @Override
    protected String getWhere() {
        return Payment.COLUMN_USER_ID + "=?";
    }

    @Override
    protected String[] getArgs() {
        return new String[]{userId + ""};
    }
}
