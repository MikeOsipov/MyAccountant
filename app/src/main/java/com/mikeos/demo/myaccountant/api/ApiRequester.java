package com.mikeos.demo.myaccountant.api;

import android.text.TextUtils;

import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.model.Client;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 23.03.17.
 */

public class ApiRequester {

    private ServerAPi api;

    public ApiRequester() {
        this.api = buildAPI(ServerAPi.SERVER, ServerAPi.class);
    }

    public BaseServerResponse addClient(Client client) {
        return makeRequest(api.addClient(client));
    }

    public BaseServerResponse updateClient(Client client) {
        return makeRequest(api.updateClient(client));
    }

    public BaseServerResponse deleteClient(Client client) {
        return makeRequest(api.deleteClient(client));
    }

    public BaseServerResponse addPayment(Payment payment) {
        return makeRequest(api.addPayment(payment));
    }

    public BaseServerResponse updatePayment(Payment payment) {
        return makeRequest(api.updatePayment(payment));
    }

    public BaseServerResponse deletePayment(Payment payment) {
        return makeRequest(api.deletePayment(payment));
    }

    private <T extends BaseServerResponse> T makeRequest(Call<T> call) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (true) {
            return ((T) new BaseServerResponse());
        }
        Response<T> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiError("Request failed");
        }
        if (response == null || !response.isSuccessful()) {
            throw new ApiError("Request failed");
        }
        T body = response.body();
        if (!body.isSuccess()) {
            String errorMessage = body.getErrorMessage();
            throw new ApiError(TextUtils.isEmpty(errorMessage) ? "Unknown error" : errorMessage);
        }
        return body;
    }

    private <T> T buildAPI(String baseURL, Class<T> apiClass) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(apiClass);
    }
}
