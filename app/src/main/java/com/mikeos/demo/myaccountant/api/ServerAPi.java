package com.mikeos.demo.myaccountant.api;

import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.model.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created on 23.03.17.
 */

public interface ServerAPi {

    String SERVER = "https://fake.com/";

    @PUT("fake_route/client")
    Call<BaseServerResponse> addClient(@Body Client client);

    @POST("fake_route/client")
    Call<BaseServerResponse> updateClient(@Body Client client);

    @DELETE("fake_route/client")
    Call<BaseServerResponse> deleteClient(@Body Client client);

    @PUT("fake_route/payment")
    Call<BaseServerResponse> addPayment(@Body Payment payment);

    @POST("fake_route/payment")
    Call<BaseServerResponse> updatePayment(@Body Payment payment);

    @DELETE("fake_route/payment")
    Call<BaseServerResponse> deletePayment(@Body Payment payment);
}
