package com.mikeos.demo.myaccountant.mvp.view;

import android.database.Cursor;
import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created on 8/29/16.
 */
public interface DBListView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showData(Cursor data);

    @StateStrategyType(SkipStrategy.class)
    void moveToAdd();

    @StateStrategyType(SkipStrategy.class)
    void onItemSelected(View v, int position, Cursor cursor, long id);
}
