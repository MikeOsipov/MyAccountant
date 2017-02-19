package com.mikeos.demo.myaccountant.ui.fragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.BaseListFragmentLayoutBinding;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;

/**
 * Created on 15.02.17.
 */

public abstract class BaseListFragment extends BaseFragment implements DBListView {

    private CursorAdapter adapter;
    private BaseListFragmentLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list_fragment_layout, null);
        binding = DataBindingUtil.bind(view);

        adapter = initAdapter();
        binding.list.setAdapter(adapter);
        binding.list.setOnItemClickListener(
                (adapterView, view1, i, l) -> getPresenter().selectedItem(view1, i, adapter.getCursor(), l));
        binding.fab.setOnClickListener(view1 -> getPresenter().onAddClicked());

        return view;
    }

    protected abstract BaseDbListPresenter getPresenter();

    protected abstract CursorAdapter initAdapter();

    @Override
    public void showData(Cursor data) {
        adapter.swapCursor(data);
    }
}
