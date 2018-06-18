package com.trivagomedia.app.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.trivagomedia.app.Base.BaseActivity;
import com.trivagomedia.app.R;
import com.trivagomedia.app.app.AppController;
import com.trivagomedia.app.modal.User;
import com.trivagomedia.app.navigation.AddNewFriendsFragment;
import com.trivagomedia.app.navigation.NewMessageFragment;
import com.trivagomedia.app.navigation.UserAccountFragment;
import com.trivagomedia.app.navigation.UserBackOfficeFragment;
import com.trivagomedia.app.navigation.UserBoardFragment;
import com.trivagomedia.app.navigation.UserFriendsFragment;
import com.trivagomedia.app.navigation.UserMessageFragment;
import com.trivagomedia.app.navigation.WriteMessageFragment;
import com.trivagomedia.app.user.LoginActivity;
import com.trivagomedia.app.utils.AppConstants;

public class HomeActivity extends BaseActivity {

    private Fragment fragment = null;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.app_name, R.string.app_name
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        setUserData();
        setNavigationClick();
        replaceFragment(new UserBoardFragment());
        setTitle("Home");

        showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 7000);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nav_userAccount:
                if (!(fragment instanceof UserAccountFragment))
                    replaceFragment(new UserAccountFragment());
                break;
            case R.id.nav_userBoard:
                if (!(fragment instanceof UserBoardFragment))
                    replaceFragment(new UserBoardFragment());
                break;
            case R.id.nav_writeMessage:
                if (!(fragment instanceof WriteMessageFragment))
                    replaceFragment(new WriteMessageFragment());
                break;
            case R.id.nav_userMessage:
                if (!(fragment instanceof UserMessageFragment))
                    replaceFragment(new UserMessageFragment());
                break;
            case R.id.nav_newMessage:
                if (!(fragment instanceof NewMessageFragment))
                    replaceFragment(new NewMessageFragment());
                break;
            case R.id.nav_userFriends:
                if (!(fragment instanceof UserFriendsFragment))
                    replaceFragment(new UserFriendsFragment());
                break;
            case R.id.nav_userBackOffice:
                if (!(fragment instanceof UserBackOfficeFragment))
                    replaceFragment(new UserBackOfficeFragment());
                break;
            case R.id.nav_addNewFriends:
                if (!(fragment instanceof AddNewFriendsFragment))
                    replaceFragment(new AddNewFriendsFragment());
                break;
            case R.id.navLogout:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure! you want to Logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppController.getInstance().isBoolean(AppConstants.IS_LOGGED_IN, false);
                                launchIntent(LoginActivity.class, true);
                            }

                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                break;
            default:
                break;
        }

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            new AlertDialog.Builder(this)
                    /*.setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing App")*/
                    .setMessage("Are you sure! you want to close App?")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            fragment = null;
            super.onBackPressed();
        }
    }

    private void setUserData() {
        Gson gson = new Gson();
        String jsonInString = AppController.getInstance().getString(AppConstants.USER_DATA);
        User user = gson.fromJson(jsonInString, User.class);

        ImageView ivAvtar = (ImageView) findViewById(R.id.nav_ivAvtar);
        TextView tvEmailId = (TextView) findViewById(R.id.nav_txtEmailId);
        TextView tvUserName = (TextView) findViewById(R.id.nav_txtUserName);

        Picasso.with(this)
                .load(user.getImgUrl())
                .placeholder(R.drawable.ic_avtar)
                .into(ivAvtar);
        tvEmailId.setText(user.getEmail());
        tvUserName.setText(user.getName());

    }

    private void setNavigationClick() {

        findViewById(R.id.nav_userAccount).setOnClickListener(this);
        findViewById(R.id.nav_userBoard).setOnClickListener(this);
        findViewById(R.id.nav_writeMessage).setOnClickListener(this);
        findViewById(R.id.nav_userMessage).setOnClickListener(this);
        findViewById(R.id.nav_newMessage).setOnClickListener(this);
        findViewById(R.id.nav_userFriends).setOnClickListener(this);
        findViewById(R.id.nav_userBackOffice).setOnClickListener(this);
        findViewById(R.id.nav_addNewFriends).setOnClickListener(this);
        findViewById(R.id.navLogout).setOnClickListener(this);
    }

    public void setPosition(int position) {
        switch (position) {
            case 0:
                getSupportActionBar().setTitle(R.string.navUserAccount);
                break;
            case 1:
                getSupportActionBar().setTitle(R.string.navUserBoard);
                break;
            case 2:
                getSupportActionBar().setTitle(R.string.navWriteMessage);
                break;
            case 3:
                getSupportActionBar().setTitle(R.string.navUserMessage);
                break;
            case 4:
                getSupportActionBar().setTitle(R.string.navNewMessage);
                break;
            case 5:
                getSupportActionBar().setTitle(R.string.navUserFriends);
                break;
            case 6:
                getSupportActionBar().setTitle(R.string.navUserBackOffice);
                break;
            case 7:
                getSupportActionBar().setTitle(R.string.navAddNewFriends);
                break;
            default:
                getSupportActionBar().setTitle("Home");
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        this.fragment = fragment;
        String backStateName = fragment.getClass().getName();
        Log.e(TAG, backStateName);
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.containerView, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

}
