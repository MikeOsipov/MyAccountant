package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.model.DbModel;

import rx.Observable;

/**
 * Created on 27.03.17.
 */

public interface Repo<T extends DbModel<T>> {

    Observable<T> create(T item);

    Observable<T> update(T item);

    Observable<T> remove(T item);

}
