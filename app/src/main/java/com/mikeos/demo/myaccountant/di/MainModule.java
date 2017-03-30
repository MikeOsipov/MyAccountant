package com.mikeos.demo.myaccountant.di;

import com.mikeos.demo.myaccountant.api.ApiRequester;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.PaymentRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 28.03.17.
 */

@Module
public class MainModule {

    @Provides
    @Singleton
    public ApiRequester provideRequester() {
        return new ApiRequester();
    }

    @Provides
    @Singleton
    public ClientRepository provideClientRepository() {
        return new ClientRepository();
    }

    @Provides
    @Singleton
    public PaymentRepository providePaymentRepository() {
        return new PaymentRepository();
    }

}
