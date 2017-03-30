package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.Repository;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.PaymentEditView;

import javax.inject.Inject;

@InjectViewState
public class PaymentEditPresenter extends BaseEditPresenter<Payment, PaymentEditView> {

    @Inject
    PaymentRepository repository;
    private long userId;

    public PaymentEditPresenter(long id, long userId) {
        super(id);
        this.userId = userId;
        MyAcApplication.getComponent().inject(this);
    }

    @Override
    protected void transformBeforeSaving(Payment item) {
        super.transformBeforeSaving(item);
        if (item.getUserId() < 0) {
            item.setUserId(userId);
        }
    }

    @Override
    protected Repository<Payment> getRepository() {
        return repository;
    }

    @Override
    public Class<Payment> getModelClass() {
        return Payment.class;
    }
}
