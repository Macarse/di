package com.test.di.analytics;

import android.content.Context;
import android.util.Log;

public class AnalyticsManager {
    private boolean initialized;

    public AnalyticsManager() {

    }

    public void init() {
        try {
            Thread.sleep(1000);
            initialized = true;
        } catch (InterruptedException e) {
        }
    }

    public void logEvent(String event) {
        Log.d(AnalyticsManager.class.getSimpleName(), event);
    }
}
