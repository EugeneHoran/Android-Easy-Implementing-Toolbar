package com.eugene.noactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FragmentMain extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main, null);
        customizeToolbar();
        changeToolbarMenu();
        return v;
    }

    boolean hasMenu = false;
    Button btnChangeMenu;

    private void changeToolbarMenu() {
        btnChangeMenu = (Button) v.findViewById(R.id.btnChangeMenu);
        btnChangeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasMenu) {
                    toolbar.getMenu().clear(); // Always clear the menu before re-inflating it
                    toolbar.inflateMenu(R.menu.menu_main_second);
                    hasMenu = true;
                } else {
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.menu_main);
                    hasMenu = false;
                }
            }
        });
    }

    Toolbar toolbar;

    private void customizeToolbar() {
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_menu_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.FromFragment();
            }
        });
        toolbar.setTitle("Hello World!"); // Setting Title of Toolbar
        toolbar.inflateMenu(R.menu.menu_main); // Inflate Toolbar menu
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {  // Handles Toolbar Menu Item Clicks
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bike) {
                    Toast.makeText(getActivity(), "Bike", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.action_settings) {
                    Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.open_apps) {
                    Toast.makeText(getActivity(), "Open Apps", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    CustomListener mCallback;

    public interface CustomListener {
        public void FromFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (CustomListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnHeadlineSelectedListener");
        }
    }
}
