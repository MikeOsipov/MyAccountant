package com.mikeos.demo.myaccountant.mvp.presenter;

import android.database.Cursor;
import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.mikeos.demo.myaccountant.MyAcApplication;
import com.squareup.sqlbrite.QueryObservable;
import com.squareup.sqlbrite.SqlBrite;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public abstract class BaseCursorPresenter<V extends MvpView> extends BaseMvpPresenter<V> {

    private Subscription subscription;
    private Cursor currentCursor;

    protected void load(Uri uri) {
        clearSubsctiption();
        if (uri == null) {
            onLoaded(null);
        } else {
            QueryObservable queryObservable = MyAcApplication.getBriteProvider()
                    .createQuery(uri, getProjection(), getWhere(), getArgs(), getSortOrder(), false);
            subscription = queryObservable.map(SqlBrite.Query::run)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((cursor) -> {
                        onLoaded(cursor);
                        closerCurrentCursor();
                        currentCursor = cursor;
                    });
        }
    }

    private void closerCurrentCursor() {
        if (currentCursor != null) {
            currentCursor.close();
            currentCursor = null;
        }
    }

    protected abstract void onLoaded(Cursor cursor);

    protected String[] getProjection() {
        return null;
    }

    protected String getWhere() {
        return null;
    }

    protected String[] getArgs() {
        return null;
    }

    protected String getSortOrder() {
        return null;
    }

    @Override
    public void onDestroy() {
        clearSubsctiption();
        super.onDestroy();
    }

    private void clearSubsctiption() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
            closerCurrentCursor();
        }
    }
}
