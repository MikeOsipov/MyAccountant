package com.mikeos.demo.myaccountant.ui.fragment;


import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.DetailsLayoutBinding;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientDetailsPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientDetailsView;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;

public class ClientDetailsFragment extends BaseFragment implements ClientDetailsView, DBListView {

    public static ClientDetailsFragment getInstance(long clientId) {
        Bundle args = new Bundle(1);
        args.putLong(BaseColumns._ID, clientId);
        ClientDetailsFragment fragment = new ClientDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    ClientDetailsPresenter detailsPresenter;
    @InjectPresenter
    PaymentListPresenter paymentListPresenter;

    @ProvidePresenter
    ClientDetailsPresenter provideDetailsPresenter() {
        return new ClientDetailsPresenter(getClientId());
    }

    @ProvidePresenter
    PaymentListPresenter providePaymentListPresenter() {
        return new PaymentListPresenter(getClientId());
    }

    private DetailsLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_layout, null);
        binding = DataBindingUtil.bind(view);
        setHasOptionsMenu(true);
        return view;
    }

    private long getClientId() {
        return getArguments().getLong(BaseColumns._ID);
    }

    private MenuItem toggle;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.client_details_menu, menu);
        toggle = menu.findItem(R.id.menu_mode_toggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_mode_toggle) {
            detailsPresenter.toggleState();
            return true;
        }
        return false;
    }

    // details

    @Override
    public void setClient(Client client) {
        binding.header.setClient(client);
        binding.info.setText("info");
    }

    @Override
    public void setPayments(Cursor data) {

    }

    @Override
    public void setPaymentListMode() {
        binding.paymentList.setVisibility(View.VISIBLE);
        binding.addButton.show();
        binding.info.setVisibility(View.GONE);
        binding.editButton.hide();
        toggle.setIcon(R.drawable.ic_info_outline_white_36dp);
    }

    @Override
    public void setDetailsMode() {
        binding.paymentList.setVisibility(View.GONE);
        binding.addButton.hide();
        binding.info.setVisibility(View.VISIBLE);
        binding.editButton.show();
        toggle.setIcon(R.drawable.ic_list_white_36dp);
    }

    // list

    @Override
    public void showData(Cursor data) {

    }

    @Override
    public void moveToAdd() {

    }

    @Override
    public void onItemSelected(View v, int position, Cursor cursor, long id) {

    }
}
