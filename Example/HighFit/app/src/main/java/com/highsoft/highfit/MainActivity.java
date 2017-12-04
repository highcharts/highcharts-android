package com.highsoft.highfit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (position){
            case 0:
                if(fragmentManager.findFragmentByTag("CHARTS") != null){
                    fragmentManager.beginTransaction()
                            .show(fragmentManager.findFragmentByTag("CHARTS"))
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new DashboardFragment(), "CHARTS")
                            .commit();
                }
                if(fragmentManager.findFragmentByTag("DATA") != null){
                    fragmentManager.beginTransaction()
                            .hide(fragmentManager.findFragmentByTag("DATA"))
                            .commit();
                }
                break;
            case 1:
                if(fragmentManager.findFragmentByTag("DATA") != null){
                    fragmentManager.beginTransaction()
                            .show(fragmentManager.findFragmentByTag("DATA"))
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new DataFragment(), "DATA")
                            .commit();
                }
                if(fragmentManager.findFragmentByTag("CHARTS") != null){
                    fragmentManager.beginTransaction()
                            .hide(fragmentManager.findFragmentByTag("CHARTS"))
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if(count == 0 ) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
        else super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("SAVED!");
    }

}
