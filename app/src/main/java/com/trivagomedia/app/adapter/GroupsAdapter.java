package com.trivagomedia.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trivagomedia.app.R;

/**
 * Created by Jitendra Soam on 1/18/2017.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.CustomViewHolder> {


    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_friends, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
