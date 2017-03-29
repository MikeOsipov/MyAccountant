package com.mikeos.demo.myaccountant.ui.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import rx.functions.Action1;

public interface IFragmentContainer {

    void addFragmentContent(Fragment fragment);

    void addFragmentContent(Fragment fragment, Action1<FragmentTransaction> transformer);

}
