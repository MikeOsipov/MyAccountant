package com.mikeos.demo.myaccountant.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created on 14.02.17.
 */

public class CommonHelper {

    public static boolean checkNullAndEmpty(@NonNull Object... objects) {
        for (Object o : objects) {
            if (o == null ||  (o instanceof String && TextUtils.isEmpty(((String) o)))) {
                return true;
            }
        }
        return false;
    }

}
