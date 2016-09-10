package com.test.di.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.test.di.MyApplication;
import com.test.di.R;
import com.test.di.analytics.AnalyticsManager;
import com.test.di.api.Api;
import com.test.di.user.UserManager;

public class LoginActivity extends Activity {

    private EditText email;
    private EditText password;
    private AnalyticsManager analyticsManager;
    private Api api;
    private UserManager userManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login);

        email = (EditText) findViewById(R.id.a_login_email);
        password = (EditText) findViewById(R.id.a_login_password);

        MyApplication myApplication = (MyApplication) getApplication();

        analyticsManager = myApplication.getAnalyticsManager();
        analyticsManager.logEvent(HomeActivity.class.getSimpleName());

        api = myApplication.getApi();

        userManager = myApplication.getUserManager();

    }

    public void onLoginClick(View v) {
        analyticsManager.logEvent("onLoginClick");

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        String apiKey = api.login(userEmail, userPassword);
        if (apiKey == null) {
            Toast.makeText(LoginActivity.this, "user/password incorrect", Toast.LENGTH_SHORT).show();
            return;
        }


        userManager.setApiKey(apiKey);
        api.setApiKey(apiKey);

        startActivity(HomeActivity.createIntent(this));
    }
}
