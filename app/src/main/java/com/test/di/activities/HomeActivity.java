package com.test.di.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import com.test.di.MyApplication;
import com.test.di.R;
import com.test.di.analytics.AnalyticsManager;
import com.test.di.api.Api;
import com.test.di.user.UserManager;

public class HomeActivity extends Activity {

    private TextView profileInfo;
    private MyApplication myApplication;
    private Api api;
    private UserManager userManager;
    private AnalyticsManager analyticsManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_home);

        profileInfo = (TextView) findViewById(R.id.a_home_profile_info);

        myApplication = (MyApplication) getApplication();
        analyticsManager = myApplication.getAnalyticsManager();
        analyticsManager.logEvent(HomeActivity.class.getSimpleName());

        api = myApplication.getApi();
        profileInfo.setText(api.getProfile());

        userManager = myApplication.getUserManager();
    }

    public void onLogoutClick(View v) {
        analyticsManager.logEvent("onLogoutClick");
        api.setApiKey(null);
        userManager.setApiKey(null);

        startActivity(LoginActivity.createIntent(this));
    }
}
