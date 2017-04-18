package com.mikeos.demo.myaccountant.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.ActivityMainBinding;
import com.mikeos.demo.myaccountant.ui.fragment.ClientListFragment;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
        implements IFragmentContainer {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragmentContent(ClientListFragment.getInstance());
        }

        getFragmentManager().addOnBackStackChangedListener(() -> {
            int count = getFragmentManager().getBackStackEntryCount();
            getSupportActionBar().setDisplayHomeAsUpEnabled(count > 1);
        });
    }

    @Override
    public void addFragmentContent(Fragment fragment) {
        addFragmentContent(fragment, null);
    }

    @Override
    public void addFragmentContent(Fragment fragment, Action1<FragmentTransaction> transformer) {
        setFragmentContent(fragment, transformer);
    }

    public void setFragmentContent(Fragment fragment, Action1<FragmentTransaction> transformer) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (transformer != null) {
            transformer.call(transaction);
        }
        transaction.replace(binding.fragmentContentFrame.getId(), fragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
