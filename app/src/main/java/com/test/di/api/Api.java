package com.test.di.api;

import android.content.Context;

import com.test.di.MyApplication;
import com.test.di.analytics.AnalyticsManager;

public class Api {

    private final AnalyticsManager analyticsManager;
    private String apiKey;

    public Api(AnalyticsManager analyticsManager) {
        this(analyticsManager, null);
    }

    public Api(AnalyticsManager analyticsManager, String apiKey) {
        this.apiKey = apiKey;
        this.analyticsManager = analyticsManager;
    }

    public String login(String email, String password) {
        analyticsManager.logEvent("login");

        if ("me@test.com".equals(email) && "pass".equals(password)) {
            return "api-key";
        }

        return null;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getProfile() {
        analyticsManager.logEvent("profile");

        if (apiKey == null) {
            throw new IllegalArgumentException("user is not logged in");
        }

        return "email=me@test.com, hello=world";
    }
}
