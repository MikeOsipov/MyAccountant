package com.mikeos.demo.myaccountant.di;

import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientEditPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentEditPresenter;

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

    void inject(ClientEditPresenter presenter);

    void inject(PaymentEditPresenter presenter);
}
