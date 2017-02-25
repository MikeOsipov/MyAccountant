package com.mikeos.demo.myaccountant.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.model.DbModel;
import com.mikeos.demo.myaccountant.mvp.view.base.BaseEditView;
import com.mikeos.demo.myaccountant.utils.DialogsHelper;

public abstract class BaseEditFragment<T extends DbModel<T>> extends BaseFragment implements BaseEditView<T> {

    protected static Bundle prepareFragment(Fragment fragment, long id) {
        Bundle args = new Bundle(1);
        args.putLong(BaseColumns._ID, id);
        fragment.setArguments(args);
        return args;
    }

    protected long getModelId() {
        return getArguments().getLong(BaseColumns._ID, -1);
    }

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.client_saving_progress));
        return view;
    }

    @LayoutRes
    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        progressDialog.dismiss();
        super.onDestroyView();
    }

    @Override
    public void onSavingBegins() {
        progressDialog.show();
    }

    @Override
    public void onSaveSuccess() {
        progressDialog.dismiss();
        getActivity().onBackPressed();
    }

    @Override
    public void onSaveFailed(String message) {
        progressDialog.dismiss();
        DialogsHelper.showErrorDialog(getActivity(), message);
    }
}
