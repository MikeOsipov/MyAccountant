package com.mikeos.demo.myaccountant.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.mikeos.demo.myaccountant.db.repository.ClientRepository;
import com.mikeos.demo.myaccountant.db.repository.Repository;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientEditView;

import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@InjectViewState
public class ClientEditPresenter extends BaseEditPresenter<Client, ClientEditView> {

    public ClientEditPresenter(long id) {
        super(id);
    }

    @Override
    protected Repository<Client> getRepository() {
        return new ClientRepository();
    }

    //    @Override
//    protected Subscription getSaveSubscription(Client update, Action1<Object> onSuccess, Action1<Throwable> onError) {
//        return Single.create(subscriber -> {
//            update.putIntoDB();
//            subscriber.onSuccess(null);
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(onSuccess, onError);
//    }

    @Override
    public Class<Client> getModelClass() {
        return Client.class;
    }
}
