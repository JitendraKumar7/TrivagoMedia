package com.trivagomedia.app.listener;

/**
 * Created by Jitendra Soam on 18/03/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.trivagomedia.app.app.AppController;


public class ConnectivityReceiver extends BroadcastReceiver {

    public static ConnectivityListener connectivityListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityListener != null) {
            connectivityListener.onNetworkChanged(isConnected);
        }
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) AppController.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public interface ConnectivityListener {
        void onNetworkChanged(boolean isConnected);
    }

}