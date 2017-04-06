package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.model.DbModel;

/**
 * Created on 06.04.17.
 */

public abstract class CursorTypedPresenter<M extends DbModel<M>, V extends MvpView> extends BaseCursorPresenter<V> {

    protected abstract Class<M> getModelClass();

    protected Uri getDataUri() {
        return AppContentProvider.getUriHelper().getUri(getModelClass());
    }

}
