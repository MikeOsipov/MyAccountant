package com.mikeos.demo.myaccountant.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.annotations.NonNull;
import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.PhoneEditListItemBinding;
import com.mikeos.demo.myaccountant.databinding.PhoneEditListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;


public class PhoneEditListView extends FrameLayout {
    public static final int NO_LIMIT = -1;

    private PhoneEditListLayoutBinding binding;

    private int limit = NO_LIMIT;
    private int count;

    public PhoneEditListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int layoutId = R.layout.phone_edit_list_layout;
        if (isInEditMode()) {
            View.inflate(context, layoutId, this);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, true);
            binding.addPhoneButton.setOnClickListener(view1 -> addNewItem(null));
            setCount(0);
        }
    }

    public void setList(List<String> list) {
        clear();
        if (list != null) {
            for (String s : list) {
                addNewItem(s);
            }
        }
        if (count == 0) {
            addNewItem(null);
        }
    }

    public void clear() {
        binding.phoneList.removeAllViews();
        setCount(0);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @NonNull
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        LinearLayout phoneList = binding.phoneList;
        for (int i = 0; i < phoneList.getChildCount(); i++) {
            View child = phoneList.getChildAt(i);
            PhoneEditListItemBinding b = ((PhoneEditListItemBinding) child.getTag());
            String phoneNumber = b.phoneText.getText().toString();
            if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() > 4) {
                list.add(phoneNumber);
            }
        }
        return list;
    }

    private void addNewItem(String content) {
        View item = View.inflate(getContext(), R.layout.phone_edit_list_item, null);
        PhoneEditListItemBinding itemBinding = DataBindingUtil.bind(item);
        binding.phoneList.addView(item);
        item.setTag(itemBinding);

        EditText phoneText = itemBinding.phoneText;

        MaskImpl mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER);
        FormatWatcher watcher = new MaskFormatWatcher(mask);
        watcher.installOn(phoneText);


        phoneText.setText(content);
        itemBinding.deleteButton.setOnClickListener(view -> {
            binding.phoneList.removeView(item);
            setCount(count - 1);
        });

        setCount(count + 1);
    }

    private void setCount(int count) {
        this.count = count;
        checkCountLimit();
    }

    private void checkCountLimit() {
        int visibility = limit > 0 && count == limit ? GONE : VISIBLE;
        Button button = binding.addPhoneButton;
        if (button.getVisibility() != visibility) {
            button.setVisibility(visibility);
        }
    }
}
