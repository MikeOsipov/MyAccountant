package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.model.DbModel;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public abstract class BaseDbModelPresenter<M extends DbModel<M>, V extends MvpView> extends CursorTypedPresenter<M, V> {
    private M model;

    public M getModel() {
        return model;
    }

    protected abstract void onModelPrepared(M model);

    public BaseDbModelPresenter(long id) {
        if (id < 0) {
            onModelPrepared(null);
        } else {
            Uri uri = ContentUris.withAppendedId(getDataUri(), id);
            load(uri);
        }
    }

    @Override
    protected void onLoaded(Cursor cursor) {
        model = cupboard().withCursor(cursor).get(getModelClass());
        onModelPrepared(model);
    }
}
