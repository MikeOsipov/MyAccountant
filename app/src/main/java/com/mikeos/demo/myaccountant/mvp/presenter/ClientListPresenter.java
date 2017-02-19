package com.mikeos.demo.myaccountant.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.db.AppContentProvider;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;

/**
 * Created on 15.02.17.
 */

@InjectViewState
public class ClientListPresenter extends BaseDbListPresenter<DBListView> {

    @Override
    protected Uri getDataUri() {
        return AppContentProvider.getUriHelper().getUri(Client.class);
    }

}
