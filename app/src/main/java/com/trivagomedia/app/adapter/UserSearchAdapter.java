package com.trivagomedia.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trivagomedia.app.R;

import java.util.List;

/**
 * Created by Jitendra Soam on 1/18/2017.
 */

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.CustomViewHolder> {

    private List<String> username;
    private static MyClickListener myClickListener;

    public UserSearchAdapter(List<String> username) {
        this.username = username;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.user_name);

            itemView.findViewById(R.id.user_name).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(v, getPosition());
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.tvName.setText(username.get(position));
    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    public void setClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

}
