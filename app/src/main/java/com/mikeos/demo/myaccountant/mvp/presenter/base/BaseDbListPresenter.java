package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.database.Cursor;
import android.view.View;

import com.mikeos.demo.myaccountant.db.specs.RepositorySpecification;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;
import com.mikeos.demo.myaccountant.utils.RxHelper;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public abstract class BaseDbListPresenter<M extends DbModel<M>, V extends DBListView> extends CursorTypedPresenter<M, V> {

    private Subscription subscription;
    private Cursor currentCursor;

    protected void load() {
        subscription = getRepository().queryCursor(getModelClass(), getSpecs())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(cursor -> {
                    onLoaded(cursor);
                    closerCurrentCursor();
                    currentCursor = cursor;
                });
    }

    private void closerCurrentCursor() {
        if (currentCursor != null) {
            currentCursor.close();
            currentCursor = null;
        }
    }

    protected RepositorySpecification<M> getSpecs() {
        return null;
    }

    private void clearSubsctiption() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
            closerCurrentCursor();
        }
    }

    @Override
    public void onDestroy() {
        clearSubsctiption();
        super.onDestroy();
    }

    private void onLoaded(Cursor cursor) {
        getViewState().showData(cursor);
    }

    public void selectedItem(View view, int position, Cursor cursor, long id) {
        getViewState().onItemSelected(view, position, cursor, id);
    }

    public void onAddClicked() {
        getViewState().moveToAdd();
    }

    public void remove(long id) {
        getRepository().get(id, getModelClass())
                .switchMap(model -> getRepository().remove(model))
                .compose(RxHelper.asyncTransformer())
                .subscribe(model -> {
                }, throwable -> getViewState().deleteFailed(throwable.getMessage()));
    }
}
