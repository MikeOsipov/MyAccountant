package com.mikeos.demo.myaccountant.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 06.04.17.
 */

public class RxHelper {

    public static <T> Observable.Transformer<T, T> asyncTransformer() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
