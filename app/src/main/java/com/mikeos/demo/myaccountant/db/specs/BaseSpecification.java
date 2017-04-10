package com.mikeos.demo.myaccountant.db.specs;

import android.net.Uri;
import android.text.TextUtils;

import com.squareup.sqlbrite.BriteContentResolver;
import com.squareup.sqlbrite.QueryObservable;

import nl.qbusict.cupboard.ProviderCompartment;

/**
 * Created on 10.04.17.
 */

public class BaseSpecification<T> implements RepositorySpecification<T> {

    private String selection;
    private String[] args;
    private String order;

    public BaseSpecification() {
    }

    public BaseSpecification(String selection, String[] args, String order) {
        this.selection = selection;
        this.args = args;
        this.order = order;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public ProviderCompartment.QueryBuilder<T> transformQuery(ProviderCompartment.QueryBuilder<T> builder) {
        if (TextUtils.isEmpty(selection)) {
            builder.withSelection(selection, args);
        }
        if (TextUtils.isEmpty(order)) {
            builder.orderBy(order);
        }
        return builder;
    }

    @Override
    public QueryObservable applyParams(Uri uri, BriteContentResolver resolver) {
        return resolver.createQuery(uri, null, selection, args, order, false);
    }
}
