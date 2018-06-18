package com.trivagomedia.app.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.trivagomedia.app.R;
import com.trivagomedia.app.adapter.MyClickListener;
import com.trivagomedia.app.adapter.PostsAdapter;
import com.trivagomedia.app.app.AppController;
import com.trivagomedia.app.home.HomeActivity;
import com.trivagomedia.app.modal.User;
import com.trivagomedia.app.utils.AppConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountFragment extends Fragment {

    private View rootView;
    private PostsAdapter mAdapter;
    private final String TAG = UserAccountFragment.class.getSimpleName();

    public UserAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_account, container, false);
        ((HomeActivity) getActivity()).setPosition(0);
        setUserData(rootView);
        initView(rootView);
        return rootView;
    }

    private void setUserData(View v) {
        Gson gson = new Gson();
        String jsonInString = AppController.getInstance().getString(AppConstants.USER_DATA);
        User user = gson.fromJson(jsonInString, User.class);

        ImageView ivAvtar = (ImageView) v.findViewById(R.id.account_ivAvtar);
        TextView tvEmailId = (TextView) v.findViewById(R.id.account_txtEmailId);
        TextView tvUserName = (TextView) v.findViewById(R.id.account_txtUserName);

        Picasso.with(getActivity())
                .load(user.getImgUrl())
                .placeholder(R.drawable.ic_avtar)
                .into(ivAvtar);
        tvEmailId.setText(user.getEmail());
        tvUserName.setText(user.getName());

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
