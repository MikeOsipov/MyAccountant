package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.db.specs.ByClientIdSpec;
import com.mikeos.demo.myaccountant.db.specs.RepositorySpecification;
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
        load();
    }

    @Override
    protected RepositorySpecification<Payment> getSpecs() {
        return new ByClientIdSpec(userId);
    }

    protected Class<Payment> getModelClass() {
        return Payment.class;
    }

    @Override
    protected Repository<Payment> getRepository() {
        return repository;
    }
}
