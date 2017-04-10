package com.mikeos.demo.myaccountant.di;

import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.db.repository.RepositoryHolder;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientDetailsPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientEditPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientListPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentEditPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 28.03.17.
 */

@Component(modules = {MainModule.class})
@Singleton
public interface MainComponent {

    void inject(PaymentRepository repository);

    void inject(ClientRepository repository);

    void inject(RepositoryHolder holder);

    void inject(ClientEditPresenter presenter);

    void inject(PaymentEditPresenter presenter);

    void inject(ClientListPresenter presenter);

    void inject(PaymentListPresenter presenter);

    void inject(ClientDetailsPresenter presenter);
}
