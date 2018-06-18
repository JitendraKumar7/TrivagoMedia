package com.trivagomedia.app.navigation;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trivagomedia.app.R;
import com.trivagomedia.app.home.HomeActivity;
import com.trivagomedia.app.pager.MessagePager;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMessageFragment extends Fragment {

    private View rootView;

    public UserMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_message, container, false);
        ((HomeActivity) getActivity()).setPosition(3);
        initView(rootView);
        return rootView;
    }

    private void initView(final View v) {

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Recently"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final MessagePager adapter = new MessagePager
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

