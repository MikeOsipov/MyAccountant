package com.mikeos.demo.myaccountant.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.ClientListItemBinding;
import com.mikeos.demo.myaccountant.model.Client;

/**
 * Created on 15.02.17.
 */

public class ClientAdapter extends CursorAdapter {

    public ClientAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = View.inflate(context, R.layout.client_list_item, null);
        ClientListItemBinding binding = DataBindingUtil.bind(view);
        view.setTag(binding);

        ViewCompat.setTransitionName(binding.root, cursor.getPosition() + "_image");

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ClientListItemBinding binding = ((ClientListItemBinding) view.getTag());
        Client client = Client.fromCursor(cursor, Client.class);
        binding.setClient(client);
    }
}
