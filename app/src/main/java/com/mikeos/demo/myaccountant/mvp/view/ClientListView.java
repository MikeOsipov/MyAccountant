package com.mikeos.demo.myaccountant.mvp.view;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created on 04.04.17.
 */

public interface ClientListView extends DBListView {

    @StateStrategyType(SkipStrategy.class)
    void deleteFailed(String msg);

}
