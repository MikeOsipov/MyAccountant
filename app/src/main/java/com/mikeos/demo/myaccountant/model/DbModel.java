package com.mikeos.demo.myaccountant.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.db.PreferredColumnOrderCursorWrapper;

import nl.qbusict.cupboard.ProviderCompartment;
import nl.qbusict.cupboard.convert.EntityConverter;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created on 14.02.17.
 */

public abstract class DbModel<T extends DbModel> {

    private Long _id;

    public DbModel() {
    }

    public DbModel(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    protected abstract Class<T> getEntityClass();

    public Uri getUri() {
        return AppContentProvider.getUriHelper().getUri(getEntityClass());
    }

    public ContentValues buildContentValues() {
        return cupboard().withEntity(getEntityClass()).toContentValues((T) this);
    }

    public void putIntoDB() {
        if (isValidId()) {
            String where = BaseColumns._ID + "=?";
            String[] args = new String[]{_id + ""};
            getProviderCompartment().update(getUri(), buildContentValues(), where, args);
        } else {
            Uri put = getProviderCompartment().put(getUri(), this);
            _id = Long.valueOf(put.getLastPathSegment());
        }
    }

    public boolean isValidId() {
        return _id != null && _id >= 0;
    }

    /*
    static section
     */

    public static <T> T byId(Long id, Class<T> entityClass) {
        return getProviderCompartment().query(AppContentProvider.getUriHelper().getUri(entityClass), entityClass)
                .withSelection(BaseColumns._ID + "=?", String.valueOf(id)).get();
    }

    public static <M extends DbModel> M fromCursor(Cursor c, Class<M> type) {
        if (c == null || c.getCount() == 0) {
            return null;
        }
        EntityConverter<M> converter = cupboard().getEntityConverter(type);
        if (c.getPosition() == -1) {
            c.moveToFirst();
        }
        Cursor wrap = new PreferredColumnOrderCursorWrapper(c, converter.getColumns());
        return converter.fromCursor(wrap);
    }

    private static ProviderCompartment getProviderCompartment() {
        return MyAcApplication.cupboard();
    }
}
