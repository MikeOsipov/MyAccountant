package com.mikeos.demo.myaccountant.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 23.03.17.
 */

public class BaseServerResponse {

    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_msg")
    private String errorMessage;

    public BaseServerResponse() {
        this.errorCode = 200;
    }

    public boolean isSuccess() {
        return errorCode > 0;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
