package com.test.di;

import android.app.Application;

import com.test.di.analytics.AnalyticsManager;
import com.test.di.api.Api;
import com.test.di.user.UserManager;

public class MyApplication extends Application {

    private AnalyticsManager analyticsManager;
    private UserManager userManager;
    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        analyticsManager = new AnalyticsManager();
        analyticsManager.init();

        userManager = new UserManager(this);

        if (userManager.isUserLoggedIn()) {
            api = new Api(analyticsManager, userManager.getApiKey());
        } else {
            api = new Api(analyticsManager);
        }
    }

    public AnalyticsManager getAnalyticsManager() {
        return analyticsManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Api getApi() {
        return api;
    }
}
