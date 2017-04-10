package com.mikeos.demo.myaccountant.mvp.presenter.base;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.DbModel;

/**
 * Created on 06.04.17.
 */

public abstract class CursorTypedPresenter<M extends DbModel<M>, V extends MvpView> extends BaseMvpPresenter<V> {

    protected abstract Repository<M> getRepository();

    protected abstract Class<M> getModelClass();

}
