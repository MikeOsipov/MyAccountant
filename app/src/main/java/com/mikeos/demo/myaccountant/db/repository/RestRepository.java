package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.api.ApiRequester;
import com.mikeos.demo.myaccountant.model.DbModel;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action2;

/**
 * Created on 28.03.17.
 */

public abstract class RestRepository<T extends DbModel<T>> extends BaseRepository<T> {

    @Inject
    ApiRequester requester;

    public RestRepository() {
    }

    @Override
    public Observable<T> create(T item) {
        return preRest(item, this::restCreate, super.create(item));
    }

    @Override
    public Observable<T> update(T item) {
        return preRest(item, this::restUpdate, super.update(item));
    }

    @Override
    public Observable<T> remove(T item) {
        return preRest(item, this::restDelete, super.remove(item));
    }

    public abstract void restCreate(T item, ApiRequester requester);

    public abstract void restUpdate(T item, ApiRequester requester);

    public abstract void restDelete(T item, ApiRequester requester);

    private Observable<T> preRest(T item, Action2<T, ApiRequester> restAction, Observable<T> src) {
        return Observable.concat(Observable.create(subscriber -> {
            restAction.call(item, requester);
            subscriber.onCompleted();
        }), src);
    }
}
