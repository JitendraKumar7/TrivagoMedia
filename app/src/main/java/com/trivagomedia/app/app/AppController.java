package com.trivagomedia.app.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.trivagomedia.app.listener.ConnectivityReceiver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jitendra Soam on 2/9/2016.
 */
public class AppController extends Application {

    private Context mContext;
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        mContext = getApplicationContext();
        mInstance = this;

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("YourKeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                System.out.println("YourKeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityListener listener) {
        ConnectivityReceiver.connectivityListener = listener;
    }

    public void isBoolean(String key, boolean data) {
        final SharedPreferences SpyAppData = mContext.getSharedPreferences(getPackageName(), 0);
        SharedPreferences.Editor editor = SpyAppData.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    public void saveString(String field, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(getPackageName(), 0);
        sp.edit().putString(field, value).commit();
    }

    public boolean getBoolean(String key) {
        final SharedPreferences ToolsAppData = mContext.getSharedPreferences(getPackageName(), 0);
        return ToolsAppData.getBoolean(key, false);
    }

    public String getString(String field) {
        SharedPreferences sp = mContext.getSharedPreferences(getPackageName(), 0);
        return sp.getString(field, null);
    }

}