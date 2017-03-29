package com.mikeos.demo.myaccountant.db.repository;

import android.net.Uri;
import android.provider.BaseColumns;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.model.DbModel;

import nl.qbusict.cupboard.ProviderCompartment;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created on 28.03.17.
 */

public class BaseRepository<T extends DbModel<T>> implements Repository<T> {

    @Override
    public Observable<T> create(T item) {
        return wrap(item, t -> {
            Uri put = getProviderCompartment().put(t.getUri(), t);
            t.setId(Long.valueOf(put.getLastPathSegment()));
        });
    }

    @Override
    public Observable<T> update(T item) {
        return wrap(item, t -> {
            String where = BaseColumns._ID + "=?";
            String[] args = new String[]{t.getId() + ""};
            getProviderCompartment().update(t.getUri(), t.buildContentValues(), where, args);
        });
    }

    @Override
    public Observable<T> remove(T item) {
        return wrap(item, t -> getProviderCompartment().delete(item.getUri(), item));
    }

    protected Observable<T> wrap(T item, Action1<T> action) {
        return Observable.create(subscriber -> {
            action.call(item);
            subscriber.onNext(item);
            subscriber.onCompleted();
        });
    }

    private ProviderCompartment getProviderCompartment() {
        return MyAcApplication.cupboard();
    }
}
