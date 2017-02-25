package com.mikeos.demo.myaccountant.ui.fragment;

import android.database.Cursor;
import android.transition.Fade;
import android.view.View;
import android.widget.CursorAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikeos.demo.myaccountant.databinding.ClientListItemBinding;
import com.mikeos.demo.myaccountant.mvp.presenter.ClientListPresenter;
import com.mikeos.demo.myaccountant.mvp.presenter.base.BaseDbListPresenter;
import com.mikeos.demo.myaccountant.ui.adapter.ClientAdapter;
import com.mikeos.demo.myaccountant.utils.DetailsTransition;

/**
 * Created on 15.02.17.
 */

public class ClientListFragment extends BaseListFragment {

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
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new DetailsTransition());

        getFragmentContainer().addFragmentContent(fragment,
                fragmentTransaction -> fragmentTransaction.addSharedElement(binding.root, "tr_test"));
    }
}
