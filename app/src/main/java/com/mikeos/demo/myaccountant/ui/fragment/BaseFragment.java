package com.mikeos.demo.myaccountant.ui.fragment;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.MvpFragment;
import com.mikeos.demo.myaccountant.ui.activity.IFragmentContainer;

/**
 * Created on 15.02.17.
 */

public class BaseFragment extends MvpFragment {

    private IFragmentContainer fragmentContainer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    private void attach(Context context) {
        fragmentContainer = ((IFragmentContainer) context);
    }

    @Override
    public void onDetach() {
        fragmentContainer = null;
        super.onDetach();
    }

    public IFragmentContainer getFragmentContainer() {
        return fragmentContainer;
    }
}
