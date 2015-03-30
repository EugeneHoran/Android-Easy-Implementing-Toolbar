package com.eugene.noactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity implements FragmentMain.CustomListener {
    Fragment fragment;
    DrawerLayout drawer;
    RelativeLayout rlNavigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new FragmentMain();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        rlNavigationDrawer = (RelativeLayout) findViewById(R.id.rlNavigationDrawer);
        rlNavigationDrawer.setOnClickListener(new View.OnClickListener() { // Just to hide the navigation drawer
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.START);
            }
        });
    }

    @Override
    public void FromFragment() {
        if (fragment != null)
            drawer.openDrawer(Gravity.START);

    }
}
