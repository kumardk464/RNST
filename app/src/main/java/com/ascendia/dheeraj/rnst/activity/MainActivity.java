package com.ascendia.dheeraj.rnst.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendia.dheeraj.rnst.R;
import com.ascendia.dheeraj.rnst.adapter.MenuListAdapter;
import com.ascendia.dheeraj.rnst.adapter.NavDrawerListAdapter;
import com.ascendia.dheeraj.rnst.fragment.RnstAboutTrust;
import com.ascendia.dheeraj.rnst.fragment.RnstContact;
import com.ascendia.dheeraj.rnst.fragment.RnstHomeFragment;
import com.ascendia.dheeraj.rnst.fragment.RnstLatestNews;
import com.ascendia.dheeraj.rnst.fragment.RnstMoments;
import com.ascendia.dheeraj.rnst.fragment.RnstUpcomingEvents;
import com.ascendia.dheeraj.rnst.fragment.RnstWhatWeDo;
import com.ascendia.dheeraj.rnst.pojo.MenuListItem;
import com.ascendia.dheeraj.rnst.pojo.NavDrawerItem;
import com.ascendia.dheeraj.rnst.popupwindow.PointerPopupWindow;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static final String PREFS_NAME = "LoginPrefs";
    private ActionBarDrawerToggle mDrawerToggle;
    PopupWindow popUp;
    Activity activity;
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
                .getResourceId(0, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
                .getResourceId(1, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
                .getResourceId(2, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
                .getResourceId(3, -1), true, "22"));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
                .getResourceId(4, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
                .getResourceId(5, -1), true, "50+"));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));


        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.rnst_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
//         actionBar.setDisplayShowCustomEnabled(true);
        // enabling action bar app icon and behaving it as toggle button

        setTitle(Html.fromHtml("<font color='#00ff00'>RNS Trust </font>"));
        LayoutInflater mInflater = LayoutInflater.from(this);

//        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
//        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
//        mTitleTextView.setText("My Own Title");
//
//        ImageButton imageButton = (ImageButton) mCustomView
//                .findViewById(R.id.imageButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//               showPopupwindow();
//            }
//        });
//
//        actionBar.setCustomView(mCustomView);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.rnst_drawer, // nav menu toggle icon
                R.string.app_name, // nav drawer open - description for
                // accessibility
                R.string.app_name // nav drawer close - description for
                // accessibility


        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.logout) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("logged");
            editor.commit();
            finish();
        }

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //   if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
      menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        //displayPopup(getApplicationContext());

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RnstHomeFragment();

                break;
            case 1:
                fragment = new RnstMoments();
                break;
            case 2:
                fragment = new RnstUpcomingEvents();

                break;
            case 3:
                fragment = new RnstLatestNews();
                break;
            case 4:
                fragment = new RnstWhatWeDo();

                break;
            case 5:
                fragment = new RnstAboutTrust();
                break;
            case 6:
                fragment = new RnstContact();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private PointerPopupWindow create() {
        // warning: you must specify the window width explicitly(do not use
        // WRAP_CONTENT or MATCH_PARENT)
        final PointerPopupWindow p = new PointerPopupWindow(this,
                getResources().getDimensionPixelSize(R.dimen.popup_width),
                getResources()
                        .getDimensionPixelSize(R.dimen.popup_content_left),
                -getResources()
                        .getDimensionPixelSize(R.dimen.popup_content_top),
                5, getResources()
                .getDimensionPixelSize(R.dimen.popup_content_bottom));


        View popupWindow = getLayoutInflater().inflate(R.layout.menu_layout,
                null);
        MenuListItem weather_data[] = new MenuListItem[]{
                // new MenuListItem(R.drawable.profile, "Profile"),
                new MenuListItem(R.drawable.about, "About"),
                new MenuListItem(R.drawable.settings, "Settings"),
                new MenuListItem(R.drawable.logout, "Logout")};

        MenuListAdapter adapter = new MenuListAdapter(this,
                R.layout.menu_list_item, weather_data);

        ListView menu_listView = (ListView) popupWindow
                .findViewById(R.id.menu_item_listview);

        menu_listView.setAdapter(adapter);
        return p;

    }
    private void showPopupwindow() {
        PointerPopupWindow p = create();
        p.setAlignMode(PointerPopupWindow.AlignMode.AUTO_OFFSET);
        p.showAsPointer(mDrawerLayout);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Rnst");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


   /* public void displayPopup(final Context context)

    {

        popUp = new PopupWindow(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.popup_layout, null, true);

        popUp = new PopupWindow(layout,  300,  125,    true);

        popUp.setBackgroundDrawable(new BitmapDrawable());

        popUp.setOutsideTouchable(true);

      //  popUp.setAnimationStyle(R.style.Animations_GrowFromBottom);

        popUp.setTouchInterceptor(new View.OnTouchListener() {


            public boolean onTouch(View v, MotionEvent event)

            {

                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)

                {

                    popUp.dismiss();

                    return true;

                }

                return false;

            }

        });


        popUp.showAtLocation(layout, Gravity.TOP, 0, 30);

    }*/
}






