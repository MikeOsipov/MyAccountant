package com.mikeos.demo.myaccountant.db.specs;

import android.net.Uri;

import com.squareup.sqlbrite.BriteContentResolver;
import com.squareup.sqlbrite.QueryObservable;

import nl.qbusict.cupboard.ProviderCompartment;

/**
 * Created on 04.04.17.
 */

public interface RepositorySpecification<T> {

    ProviderCompartment.QueryBuilder<T> transformQuery(ProviderCompartment.QueryBuilder<T> builder);

    QueryObservable applyParams(Uri uri, BriteContentResolver resolver);

}
