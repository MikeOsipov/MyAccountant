package com.mikeos.demo.myaccountant.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mikeos.demo.myaccountant.R;
import com.mikeos.demo.myaccountant.databinding.ActivityMainBinding;
import com.mikeos.demo.myaccountant.ui.fragment.ClientListFragment;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragmentContainer {

    private DrawerLayout drawerLayout;
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);


        drawerLayout = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);
        contentFrame = binding.fragmentContentFrame;

        if (savedInstanceState == null) {
            addFragmentContent(ClientListFragment.getInstance());
        }
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
        transaction.replace(contentFrame.getId(), fragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
