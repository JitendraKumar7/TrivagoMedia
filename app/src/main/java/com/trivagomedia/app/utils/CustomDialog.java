package com.trivagomedia.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.trivagomedia.app.R;


/**
 * Created by Jitendra Kumar Soam on 2/26/2016.
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_progrees_bar, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(v);
        setCancelable(false);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
