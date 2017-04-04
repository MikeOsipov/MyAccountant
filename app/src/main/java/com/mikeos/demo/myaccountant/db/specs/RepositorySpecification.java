package com.mikeos.demo.myaccountant.db.specs;

import nl.qbusict.cupboard.ProviderCompartment;

/**
 * Created on 04.04.17.
 */

public interface RepositorySpecification<T> {

    ProviderCompartment.QueryBuilder<T> transformQuery(ProviderCompartment.QueryBuilder<T> builder);

}
