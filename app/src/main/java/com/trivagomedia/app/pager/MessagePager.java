package com.trivagomedia.app.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trivagomedia.app.message.FriendsFragment;
import com.trivagomedia.app.message.GroupsFragment;
import com.trivagomedia.app.message.RecentlyFragment;


/**
 * Created by Jitendra Soam on 1/18/2017.
 */

public class MessagePager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;

    //Constructor to the class
    public MessagePager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new RecentlyFragment();
            case 1:
                return new FriendsFragment();
            case 2:
                return new GroupsFragment();
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}