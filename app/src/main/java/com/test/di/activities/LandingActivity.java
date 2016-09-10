package com.test.di.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.di.MyApplication;
import com.test.di.R;
import com.test.di.analytics.AnalyticsManager;
import com.test.di.user.UserManager;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_landing);

        MyApplication myApplication = ((MyApplication) getApplication());

        AnalyticsManager analyticsManager = myApplication.getAnalyticsManager();
        analyticsManager.logEvent(LandingActivity.class.getSimpleName());

        UserManager userManager = myApplication.getUserManager();
        if (userManager.isUserLoggedIn()) {
            startActivity(HomeActivity.createIntent(this));
        } else {
            startActivity(LoginActivity.createIntent(this));
        }

    }
}
