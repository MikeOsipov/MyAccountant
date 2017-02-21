package com.mikeos.demo.myaccountant.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.mikeos.demo.myaccountant.R;

/**
 * Created on 21.02.17.
 */

public class DialogsHelper {


    public static AlertDialog showErrorDialog(Context context, String message) {
        return showAlertDialog(context, message, context.getString(R.string.error));
    }

    public static AlertDialog showAlertDialog(Context context, String message) {
        return showAlertDialog(context, message, null);
    }

    public static AlertDialog showAlertDialog(Context context, String message, String title) {
        AlertDialog.Builder builder = getBuilder(context).setMessage(message).setPositiveButton(android.R.string.ok, null);
        if (title != null) {
            builder.setTitle(title);
        }
        return builder.show();
    }


    public static AlertDialog.Builder getBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }
}
