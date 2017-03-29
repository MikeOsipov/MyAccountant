package com.mikeos.demo.myaccountant.di;

import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;

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
}
