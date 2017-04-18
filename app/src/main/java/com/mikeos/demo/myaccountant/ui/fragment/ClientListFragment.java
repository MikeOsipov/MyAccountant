package com.mikeos.demo.myaccountant.ui.fragment;

import android.database.Cursor;
import android.transition.Fade;
import android.view.View;
import android.widget.CursorAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.ClientListItemBinding;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientListPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.mvp.view.ClientListView;
import com.mikeos.demo.myaccountant.mvp.view.DBListView;
import com.mikeos.demo.myaccountant.ui.adapter.ClientAdapter;
import com.mikeos.demo.myaccountant.utils.ClientItemTransition;
import com.mikeos.demo.myaccountant.utils.DialogsHelper;

/**
 * Created on 15.02.17.
 */

public class ClientListFragment extends BaseListFragment implements DBListView {

    public static ClientListFragment getInstance() {
        return new ClientListFragment();
    }

    @InjectPresenter
    ClientListPresenter presenter;

    @Override
    protected BaseDbListPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected CustomViewData customView() {
        return new CustomViewData(R.layout.client_list_fragment, R.id.content);
    }

    @Override
    protected CursorAdapter initAdapter() {
        return new ClientAdapter(getActivity(), null);
    }

    @Override
    public void moveToAdd() {
        getFragmentContainer().addFragmentContent(ClientEditFragment.getAddInstance());
    }

    @Override
    public void onItemSelected(View v, int position, Cursor cursor, long id) {
        ClientListItemBinding binding = ((ClientListItemBinding) v.getTag());

        ClientDetailsFragment fragment = ClientDetailsFragment.getInstance(id);
        fragment.setSharedElementEnterTransition(new ClientItemTransition());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new ClientItemTransition());

        getFragmentContainer().addFragmentContent(fragment,
                fragmentTransaction -> fragmentTransaction.addSharedElement(binding.root, "tr_test"));
    }

}
