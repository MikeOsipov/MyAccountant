package com.mikeos.demo.myaccountant.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.PaymentListItemBinding;
import com.mikeos.demo.myaccountant.model.Payment;


public class PaymentAdapter extends CursorAdapter {

    public PaymentAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = View.inflate(context, R.layout.payment_list_item, null);
        PaymentListItemBinding binding = DataBindingUtil.bind(view);
        view.setTag(binding);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PaymentListItemBinding binding = ((PaymentListItemBinding) view.getTag());
        binding.setPay(Payment.fromCursor(cursor, Payment.class));
    }
}
