package com.mikeos.demo.myaccountant.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.ClientEditLayoutBinding;
import com.mikeos.demo.myaccountant.model.client.Client;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientEditView;

import rx.functions.Func1;

public class ClientEditFragment extends BaseEditFragment<Client> implements ClientEditView {

    public static ClientEditFragment getAddInstance() {
        return getEditInstance(-1);
    }

    public static ClientEditFragment getEditInstance(long id) {
        ClientEditFragment fragment = new ClientEditFragment();
        prepareFragment(fragment, id);
        return fragment;
    }

    @InjectPresenter
    ClientEditPresenter presenter;

    @ProvidePresenter
    public ClientEditPresenter buildPresenter() {
        return new ClientEditPresenter(getModelId());
    }

    private ClientEditLayoutBinding binding;

    @Override
    protected int getLayout() {
        return R.layout.client_edit_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.phoneListView.setLimit(Client.PHONE_LIST_LIMIT);
        binding.saveButton.setOnClickListener(view1 -> saveClient());
        return view;
    }

    private void saveClient() {
        try {
            Func1<EditText, String> textOf = editText -> editText.getText().toString();
            Client client = new Client(
                    textOf.call(binding.name),
                    binding.phoneListView.getList(),
                    textOf.call(binding.vat),
                    textOf.call(binding.bank),
                    textOf.call(binding.account), 0);
            presenter.saveModel(client);
        } catch (Exception e) {
            e.printStackTrace();
            onSaveFailed(e.getMessage());
        }
    }

    @Override
    public void showModel(Client client) {
        binding.setClient(client);
        boolean onEdit = client != null;
        binding.phoneListView.setList(onEdit ? client.getPhoneList() : null);
        binding.saveButton.setText(onEdit
                ? R.string.client_button_save : R.string.client_button_create);
    }
}
