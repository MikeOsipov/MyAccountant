package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import com.mikeos.demo.myaccountant.db.repository.base.Repository;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;
import com.mikeos.demo.myaccountant.utils.RxHelper;

public abstract class BaseDbListPresenter<M extends DbModel<M>, V extends DBListView> extends CursorTypedPresenter<M, V> {

    public BaseDbListPresenter() {
        Uri dataUri = getDataUri();
        if (dataUri != null) {
            load(dataUri);
        }
    }

    public void onLoaded(Cursor cursor) {
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

    protected abstract Repository<M> getRepository();
}
