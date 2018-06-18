package com.trivagomedia.app;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.trivagomedia.app.Base.BaseActivity;
import com.trivagomedia.app.app.AppController;
import com.trivagomedia.app.home.HomeActivity;
import com.trivagomedia.app.user.LoginActivity;
import com.trivagomedia.app.utils.AppConstants;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppController.getInstance().getBoolean(AppConstants.IS_LOGGED_IN)) {
                    avi.hide();
                    launchIntent(HomeActivity.class, true);
                } else {
                    avi.hide();
                    launchIntent(LoginActivity.class, true);
                }
            }
        }, 3000);

    }

    @Override
    public void onClick(View v) {

    }

}
