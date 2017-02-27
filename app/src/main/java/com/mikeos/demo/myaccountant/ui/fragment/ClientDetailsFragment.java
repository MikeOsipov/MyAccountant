package com.mikeos.demo.myaccountant.ui.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.DetailsLayoutBinding;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientDetailsPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientDetailsView;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;
import com.mikeos.demo.myaccountant.ui.adapter.PaymentAdapter;
import com.mikeos.demo.myaccountant.utils.DialogsHelper;

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
    private MenuItem toggle;

    private PaymentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_layout, null);
        binding = DataBindingUtil.bind(view);
        binding.addButton.setOnClickListener(v -> paymentListPresenter.onAddClicked());
        binding.editButton.setOnClickListener(v -> detailsPresenter.handleEdit());

        adapter = new PaymentAdapter(getActivity(), null);
        binding.paymentList.setAdapter(adapter);

        return view;
    }

    private long getClientId() {
        return getArguments().getLong(BaseColumns._ID);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.client_details_menu, menu);
        toggle = menu.findItem(R.id.menu_mode_toggle);
        updateToggle();
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
        binding.info.setText(getString(R.string.client_additional_info_format,
                client.getVat(), client.getBankName(), client.getBankAccount()));
        binding.phoneListFrame.removeAllViews();
        for (String phone : client.getPhoneList()) {
            TextView textView = ((TextView) View.inflate(getActivity(), R.layout.phone_number_text_view, null));
            textView.setText(phone);
            textView.setOnClickListener(v -> detailsPresenter.handlePhoneTap(((TextView) v).getText().toString()));
            binding.phoneListFrame.addView(textView);
        }
    }

    @Override
    public void setPayments(Cursor data) {

    }

    @Override
    public void setPaymentListMode() {
        binding.paymentList.setVisibility(View.VISIBLE);
        binding.addButton.show();
        binding.infoFrame.setVisibility(View.GONE);
        binding.editButton.hide();
        updateToggle();
    }

    @Override
    public void setDetailsMode() {
        binding.paymentList.setVisibility(View.GONE);
        binding.addButton.hide();
        binding.infoFrame.setVisibility(View.VISIBLE);
        binding.editButton.show();
        updateToggle();
    }

    private void updateToggle() {
        if (toggle != null) {
            toggle.setIcon(detailsPresenter.isListMode() ?
                    R.drawable.ic_info_outline_white_36dp : R.drawable.ic_list_white_36dp);
        }
    }

    @Override
    public void moveToCall(String number) {
        DialogsHelper.getBuilder(getActivity())
                .setMessage(getString(R.string.client_call_intention_message, number))
                .setNegativeButton(R.string.client_call_intention_cancel, null)
                .setPositiveButton(R.string.client_call_intention_ok, (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                    startActivity(intent);
                }).show();
    }

    @Override
    public void moveToEdit(long clientId) {
        getFragmentContainer().addFragmentContent(ClientEditFragment.getEditInstance(clientId));
    }

    // list

    @Override
    public void showData(Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void moveToAdd() {
        getFragmentContainer().addFragmentContent(PaymentEditFragment.getAddInstance(getClientId()));
    }

    @Override
    public void onItemSelected(View v, int position, Cursor cursor, long id) {

    }

    @Override
    public void onDestroyView() {
        adapter.swapCursor(null);
        super.onDestroyView();
    }
}
