package com.mikeos.demo.myaccountant.db.repository;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.model.DbModel;

import nl.qbusict.cupboard.ProviderCompartment;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * Created on 28.03.17.
 */

public class BaseRepository<T extends DbModel<T>> implements Repository<T> {

    @Override
    public Observable<T> query(long id, Class<T> tClass) {
        Uri uri = getUriForId(id, tClass);
        return MyAcApplication.getBriteProvider()
                .createQuery(uri, null, null, null, null, false).map(query -> {
                    Cursor cursor = query.run();
                    T model = DbModel.fromCursor(cursor, tClass);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return model;
                });
    }

    @Override
    public Observable<T> get(long id, Class<T> tClass) {
        return wrap(() -> getProviderCompartment().query(getUriForId(id, tClass), tClass).get(), null);
    }

    @Override
    public Observable<T> create(T item) {
        return wrap(() -> item, t -> {
            Uri put = getProviderCompartment().put(t.getUri(), t);
            t.setId(Long.valueOf(put.getLastPathSegment()));
        });
    }

    @Override
    public Observable<T> update(T item) {
        return wrap(() -> item, t -> {
            String where = BaseColumns._ID + "=?";
            String[] args = new String[]{t.getId() + ""};
            getProviderCompartment().update(t.getUri(), t.buildContentValues(), where, args);
        });
    }

    @Override
    public Observable<T> remove(T item) {
        return wrap(() -> item, t -> getProviderCompartment().delete(item.getUri(), item));
    }

    private Uri getUriForId(long id, Class<T> tClass) {
        return ContentUris.withAppendedId(AppContentProvider.getUriHelper().getUri(tClass), id);
    }

    protected Observable<T> wrap(Func0<T> item, Action1<T> action) {
        return Observable.create(subscriber -> {
            T model = item.call();
            if (action != null) {
                action.call(model);
            }
            subscriber.onNext(model);
            subscriber.onCompleted();
        });
    }

    private ProviderCompartment getProviderCompartment() {
        return MyAcApplication.cupboard();
    }
}