package com.mikeos.demo.myaccountant.db.repository;

import com.mikeos.demo.myaccountant.MyAcApplication;
import com.mikeos.demo.myaccountant.api.ApiRequester;
import com.mikeos.demo.myaccountant.db.repository.base.RestRepository;
import com.mikeos.demo.myaccountant.model.Client;

/**
 * Created on 28.03.17.
 */

public class ClientRepository extends RestRepository<Client> {

    public ClientRepository() {
        MyAcApplication.getComponent().inject(this);
    }

    @Override
    public void restCreate(Client item, ApiRequester requester) {
        requester.addClient(item);
    }

    @Override
    public void restUpdate(Client item, ApiRequester requester) {
        requester.updateClient(item);
    }

    @Override
    public void restDelete(Client item, ApiRequester requester) {
        requester.deleteClient(item);
    }
}
