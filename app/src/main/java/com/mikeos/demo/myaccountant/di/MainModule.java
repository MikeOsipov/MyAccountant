package com.mikeos.demo.myaccountant.di;

import com.mikeos.demo.myaccountant.api.ApiRequester;

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
    public ApiRequester provideRequester(){
        return new ApiRequester();
    }

}
