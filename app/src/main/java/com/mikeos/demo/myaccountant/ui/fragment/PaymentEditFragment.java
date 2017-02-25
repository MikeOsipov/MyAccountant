package com.mikeos.demo.myaccountant.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.PaymentEditLayoutBinding;
import com.mikeos.demo.myaccountant.model.Payment;
import com.mikeos.demo.myaccountant.mvp.presenter.PaymentEditPresenter;
import com.mikeos.demo.myaccountant.mvp.view.PaymentEditView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaymentEditFragment extends BaseEditFragment<Payment> implements PaymentEditView {

    private static final String KEY_USER_ID = "key_user_id";

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

    public static PaymentEditFragment getAddInstance(long userId) {
        return getEditInstance(-1, userId);
    }

    public static PaymentEditFragment getEditInstance(long id, long userId) {
        PaymentEditFragment fragment = new PaymentEditFragment();
        Bundle bundle = prepareFragment(fragment, id);
        bundle.putLong(KEY_USER_ID, userId);
        return fragment;
    }

    @InjectPresenter
    PaymentEditPresenter presenter;

    @ProvidePresenter
    PaymentEditPresenter buildPresenter() {
        return new PaymentEditPresenter(getModelId(), getArguments().getLong(KEY_USER_ID));
    }

    private PaymentEditLayoutBinding binding;
    private Date date;

    @Override
    protected int getLayout() {
        return R.layout.payment_edit_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.submitButton.setOnClickListener(view1 -> save());
        binding.dateButton.setOnClickListener(view1 -> editDate());
        return view;
    }

    private void save() {
        String sum = binding.sum.getText().toString();
        presenter.saveModel(new Payment(Long.valueOf(sum), -1, date));
    }

    private void editDate() {
        Calendar current = Calendar.getInstance();
        current.setTime(date);
        DatePickerDialog dialog = DatePickerDialog.newInstance(
                (view, year, monthOfYear, dayOfMonth, yearEnd, monthOfYearEnd, dayOfMonthEnd) -> {
                    current.set(Calendar.YEAR, year);
                    current.set(Calendar.MONTH, monthOfYear);
                    current.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    setDate(current.getTime());
                },
                current.get(Calendar.YEAR),
                current.get(Calendar.MONTH),
                current.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void showModel(Payment payment) {
        Date newDate;
        if (payment == null) {
            binding.submitButton.setText(R.string.payment_edit_create);
            newDate = Calendar.getInstance().getTime();
        } else {
            binding.submitButton.setText(R.string.payment_edit_save);
            binding.sum.setText(payment.getSum() + "");
            newDate = payment.getDate();
        }
        setDate(newDate);
    }

    public void setDate(Date date) {
        this.date = date;
        binding.dateButton.setText(FORMAT.format(date));
    }

}
