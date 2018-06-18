package com.trivagomedia.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trivagomedia.app.R;

/**
 * Created by Jitendra Soam on 1/18/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.CustomViewHolder> {

    private Context mContext;
    private static MyClickListener myClickListener;

    public PostsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivAvtar, ivMainView;
        private TextView tvName, tvDate, tvComments, tvCountLike, tvCountComments, tvBtnLike;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ivAvtar = (ImageView) itemView.findViewById(R.id.post_ivAvtar);
            tvName = (TextView) itemView.findViewById(R.id.post_txtName);
            tvDate = (TextView) itemView.findViewById(R.id.post_txtDate);

            tvBtnLike = (TextView) itemView.findViewById(R.id.post_txtBtnLike);
            tvComments = (TextView) itemView.findViewById(R.id.post_txtComments);
            ivMainView = (ImageView) itemView.findViewById(R.id.post_ivMainView);
            tvCountLike = (TextView) itemView.findViewById(R.id.post_txtCountLike);
            tvCountComments = (TextView) itemView.findViewById(R.id.post_txtCountComments);

            itemView.findViewById(R.id.post_ivBtnLike).setOnClickListener(this);
            itemView.findViewById(R.id.post_txtBtnLike).setOnClickListener(this);
            itemView.findViewById(R.id.post_txtCountLike).setOnClickListener(this);
            itemView.findViewById(R.id.post_ivBtnComments).setOnClickListener(this);
            itemView.findViewById(R.id.post_txtBtnComments).setOnClickListener(this);
            itemView.findViewById(R.id.post_ivBtnRecommend).setOnClickListener(this);
            itemView.findViewById(R.id.post_txtBtnRecommend).setOnClickListener(this);
            itemView.findViewById(R.id.post_txtCountComments).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(v, getPosition());
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_posts, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public void setClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

}
