package com.mikeos.demo.myaccountant.utils;

import android.content.Context;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.util.AttributeSet;

public class ClientItemTransition extends TransitionSet {

    public ClientItemTransition() {
        init();
    }

    public ClientItemTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform());
    }
}
