package com.trivagomedia.app.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trivagomedia.app.R;
import com.trivagomedia.app.adapter.MyClickListener;
import com.trivagomedia.app.adapter.PostsAdapter;
import com.trivagomedia.app.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserBoardFragment extends Fragment {

    private View rootView;
    private PostsAdapter mAdapter;
    private final String TAG = UserBoardFragment.class.getSimpleName();

    public UserBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_board, container, false);
        ((HomeActivity) getActivity()).setPosition(1);
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        mAdapter = new PostsAdapter(getActivity());
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ((PostsAdapter) mAdapter).setClickListener(new MyClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                String click = null;
                switch (v.getId()) {
                    case R.id.post_ivBtnLike:
                    case R.id.post_txtBtnLike:
                        click = "post_txtBtnLike";
                        break;

                    case R.id.post_txtCountLike:
                        click = "post_txtCountLike";
                        break;

                    case R.id.post_ivBtnComments:
                    case R.id.post_txtBtnComments:
                        click = "post_txtBtnComments";
                        break;

                    case R.id.post_ivBtnRecommend:
                    case R.id.post_txtBtnRecommend:
                        click = "post_txtBtnRecommend";
                        break;

                    case R.id.post_txtCountComments:
                        click = "post_txtCountComments";
                        break;

                    default:
                        click = "default";
                        break;
                }

                Log.i(TAG, click);

            }
        });
    }


}
