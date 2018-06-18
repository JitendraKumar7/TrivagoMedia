package com.trivagomedia.app.user;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.gson.Gson;
import com.trivagomedia.app.Base.SocialIntegration;
import com.trivagomedia.app.R;
import com.trivagomedia.app.app.AppController;
import com.trivagomedia.app.home.HomeActivity;
import com.trivagomedia.app.listener.ConnectivityReceiver;
import com.trivagomedia.app.modal.User;
import com.trivagomedia.app.utils.AppConstants;

import org.json.JSONObject;

public class LoginActivity extends SocialIntegration {

    private EditText txtUsername, txtPassword;
    private TextView tvBtnForgot, tvBtnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        txtUsername = (EditText) findViewById(R.id.login_txtUsername);
        txtPassword = (EditText) findViewById(R.id.login_txtPassword);

        tvBtnForgot = (TextView) findViewById(R.id.login_txtBtnForgot);
        tvBtnSignup = (TextView) findViewById(R.id.login_txtBtnSignup);

        tvBtnForgot.setPaintFlags(tvBtnSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvBtnSignup.setPaintFlags(tvBtnSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvBtnForgot.setOnClickListener(this);
        tvBtnSignup.setOnClickListener(this);

        findViewById(R.id.login_btnGoogle).setOnClickListener(this);
        findViewById(R.id.login_btnFacebook).setOnClickListener(this);
        findViewById(R.id.login_txtBtnLogin).setOnClickListener(this);
    }

    private void attempLogin() {
        String loginUserId = txtUsername.getText().toString().trim();
        String loginPassword = txtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(loginUserId)) {
            txtUsername.setError("Required");
        } else if (TextUtils.isEmpty(loginPassword)) {
            txtPassword.setError("Required");
        } else if (loginPassword.length() > 5) {
            if (ConnectivityReceiver.isConnected()) {
                //AppController.getInstance().isBoolean(AppConstants.IS_LOGGED_IN, true);
                //launchIntent(HomeActivity.class, true);
            } else {
                showToast("Network Error! Please check your network settings.");
            }
        } else {
            txtPassword.setError("Password length min 6 character");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_txtBtnForgot:
                launchIntent(ForgotActivity.class, true);
                break;

            case R.id.login_txtBtnLogin:
                attempLogin();
                break;

            case R.id.login_btnGoogle:
                googleSignIn();
                break;

            case R.id.login_btnFacebook:
                facebookSignIn();
                break;

            case R.id.login_txtBtnSignup:
                launchIntent(SignupActivity.class, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void handleSignInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            try {
                String personId = acct.getId();
                String personEmail = acct.getEmail();
                String personName = acct.getDisplayName();
                String personPhotoUrl = acct.getPhotoUrl().toString();

                Log.e(TAG, "id: " + personId + "Name: " + personName +
                        ", email: " + personEmail + ", Image: " + personPhotoUrl);

                Gson gson = new Gson();
                User user = new User();
                user.setName(personName);
                user.setEmail(personEmail);
                user.setImgUrl(personPhotoUrl);
                String jsonInString = gson.toJson(user);
                AppController.getInstance().isBoolean(AppConstants.IS_LOGGED_IN, true);
                AppController.getInstance().saveString(AppConstants.USER_DATA, jsonInString);
                launchIntent(HomeActivity.class, true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleSignInResult(JSONObject object) {
        try {
            String personId = object.getString("id").toString();
            String personName = object.getString("name").toString();
            String facebook_gender = object.getString("gender").toString();
            if (object.has("email")) {
                String personEmail = object.getString("email").toString();
                String personPhotoUrl = "https://graph.facebook.com/" + personId + "/picture?type=large";

                Log.e(TAG, "id: " + personId + "Name: " + personName +
                        ", email: " + personEmail + ", Image: " + personPhotoUrl);
                Gson gson = new Gson();
                User user = new User();
                user.setName(personName);
                user.setEmail(personEmail);
                user.setImgUrl(personPhotoUrl);
                String jsonInString = gson.toJson(user);
                AppController.getInstance().isBoolean(AppConstants.IS_LOGGED_IN, true);
                AppController.getInstance().saveString(AppConstants.USER_DATA, jsonInString);
                launchIntent(HomeActivity.class, true);
            } else {
                Log.e(TAG, "Facebook Email id issue");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}