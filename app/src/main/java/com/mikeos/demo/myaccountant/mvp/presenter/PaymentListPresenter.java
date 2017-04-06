package com.mikeos.demo.myaccountant.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;

import javax.inject.Inject;


@InjectViewState
public class PaymentListPresenter extends BaseDbListPresenter<Payment, DBListView> {

    @Inject
    PaymentRepository repository;
    private Long userId;

    public PaymentListPresenter(long userId) {
        MyAcApplication.getComponent().inject(this);
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

    @Override
    protected Class<Payment> getModelClass() {
        return Payment.class;
    }

    @Override
    protected Repository<Payment> getRepository() {
        return repository;
    }
}
