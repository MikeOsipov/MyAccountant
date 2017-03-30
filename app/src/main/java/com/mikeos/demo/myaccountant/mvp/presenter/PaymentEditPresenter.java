package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.Repository;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.PaymentEditView;

@InjectViewState
public class PaymentEditPresenter extends BaseEditPresenter<Payment, PaymentEditView> {

    private long userId;

    public PaymentEditPresenter(long id, long userId) {
        super(id);
        this.userId = userId;
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
        return new PaymentRepository();
    }

    @Override
    public Class<Payment> getModelClass() {
        return Payment.class;
    }
}
