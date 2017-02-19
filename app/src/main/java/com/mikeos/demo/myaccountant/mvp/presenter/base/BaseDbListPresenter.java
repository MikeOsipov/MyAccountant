package com.mikeos.demo.myaccountant.mvp.presenter.base;

import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import com.mikeos.demo.myaccountant.mvp.view.DBListView;

public abstract class BaseDbListPresenter<V extends DBListView> extends BaseCursorPresenter<V> {

    public BaseDbListPresenter() {
        Uri dataUri = getDataUri();
        if (dataUri != null) {
            load(dataUri);
        }
    }

    protected abstract Uri getDataUri();

    public void onLoaded(Cursor cursor) {
        getViewState().showData(cursor);
    }

    public void selectedItem(View view, int position, Cursor cursor, long id) {
        getViewState().onItemSelected(view, position, cursor, id);
    }

    public void onAddClicked() {
        getViewState().moveToAdd();
    }

}
