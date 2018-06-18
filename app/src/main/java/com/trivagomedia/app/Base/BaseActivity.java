package com.trivagomedia.app.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.trivagomedia.app.app.AppController;
import com.trivagomedia.app.listener.ConnectivityReceiver;
import com.trivagomedia.app.utils.CustomDialog;


/**
 * Created by Jitendra Soam on 1/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,
        ConnectivityReceiver.ConnectivityListener {

    private Toast toast;
    public Activity activity;
    private CustomDialog pDialog;
    public final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        activity = this;
        pDialog = new CustomDialog(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public void dismissDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        if (!toast.getView().isShown()) {
            toast.setText(message);
            toast.show();
        }
    }

    public void launchIntent(Class<? extends Activity> cls, boolean finish) {
        Intent intent = new Intent(activity, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        if (finish)
            finish();

    }

    public void launchIntent(Class<? extends Activity> cls, Bundle bundle, boolean finish) {
        Intent intent = new Intent(activity, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        startActivity(intent);
        if (finish)
            finish();

    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (!isConnected) {
            showToast("Network Error! Please check your network settings.");
        }
    }

}
