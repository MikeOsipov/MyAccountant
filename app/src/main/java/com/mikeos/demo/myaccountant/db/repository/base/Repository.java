package com.mikeos.demo.myaccountant.db.repository.base;

import android.database.Cursor;

import com.mikeos.demo.myaccountant.db.specs.RepositorySpecification;
import com.mikeos.demo.myaccountant.model.DbModel;

import nl.qbusict.cupboard.QueryResultIterable;
import rx.Observable;

/**
 * Created on 27.03.17.
 */

public interface Repository<T extends DbModel<T>> {

    Observable<T> query(long id, Class<T> tClass);

    Observable<T> get(long id, Class<T> tClass);

    //    Observable<Cursor> queryCursor();
//
    Observable<QueryResultIterable<T>> getIterable(RepositorySpecification<T> specification, Class<T> tClass);

    Observable<T> create(T item);

    Observable<T> update(T item);

    Observable<T> remove(T item);
}
