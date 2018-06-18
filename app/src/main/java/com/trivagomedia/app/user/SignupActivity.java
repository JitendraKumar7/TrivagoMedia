package com.trivagomedia.app.user;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.trivagomedia.app.Base.BaseActivity;
import com.trivagomedia.app.R;


public class SignupActivity extends BaseActivity {

    private EditText txtFirstame, txtLastname, txtUsername,
            txtEmailid, txtPassword, txtConPassword, txtInviterid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {

        txtFirstame = (EditText) findViewById(R.id.signup_txtFirstname);
        txtLastname = (EditText) findViewById(R.id.signup_txtLastname);
        txtUsername = (EditText) findViewById(R.id.signup_txtUsername);
        txtEmailid = (EditText) findViewById(R.id.signup_txtEmailid);
        txtPassword = (EditText) findViewById(R.id.signup_txtPassword);
        txtInviterid = (EditText) findViewById(R.id.signup_txtInviter);
        txtConPassword = (EditText) findViewById(R.id.signup_txtConPassword);

        findViewById(R.id.signup_txtBtnRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signup_txtBtnRegister:
                //launchIntent(HomeActivity.class, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        launchIntent(LoginActivity.class, true);
    }

}