package com.trivagomedia.app.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trivagomedia.app.R;
import com.trivagomedia.app.adapter.BackOfficeAdapter;
import com.trivagomedia.app.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserBackOfficeFragment extends Fragment {

    private View rootView;

    public UserBackOfficeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_back_office, container, false);
        ((HomeActivity) getActivity()).setPosition(6);
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new BackOfficeAdapter());
    }

}
