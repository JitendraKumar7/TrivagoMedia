package com.trivagomedia.app.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Jitendra Soam on 1/20/2017.
 */

public abstract class SocialIntegration extends BaseActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        loginButton = new LoginButton(this);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void facebookSignIn() {
        loginButton.performClick();
        loginButton.setPressed(true);
        loginButton.invalidate();
        loginButton.registerCallback(callbackManager, mCallBack);
        loginButton.setPressed(false);
        loginButton.invalidate();
    }

    public abstract void handleSignInResult(Intent data);

    public abstract void handleSignInResult(JSONObject object);

    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            LoginManager.getInstance().logOut();
                            handleSignInResult(object);
                        }

                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, name, email, gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            //pDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            //pDialog.dismiss();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            handleSignInResult(data);
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            //updateUI(false);
                        }
                    });
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}




       /* */


        /**/
