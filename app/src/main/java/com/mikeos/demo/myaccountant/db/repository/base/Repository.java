package com.mikeos.demo.myaccountant.db.repository.base;

import com.mikeos.demo.myaccountant.model.DbModel;

import rx.Observable;

/**
 * Created on 27.03.17.
 */

public interface Repository<T extends DbModel<T>> {

    Observable<T> query(long id, Class<T> tClass);

    Observable<T> get(long id, Class<T> tClass);

    Observable<T> create(T item);

    Observable<T> update(T item);

    Observable<T> remove(T item);

}
