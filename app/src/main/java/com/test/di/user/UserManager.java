package com.test.di.user;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    private final SharedPreferences prefs;

    public UserManager(Context context) {
        prefs = context.getSharedPreferences(UserManager.class.getSimpleName(), Context.MODE_PRIVATE);
    }

    public boolean isUserLoggedIn() {
        return prefs.getString("api_key", null) != null;
    }

    public void setApiKey(String apiKey) {
        if (apiKey == null) {
            prefs.edit().remove("api_key").apply();
        } else {
            prefs.edit().putString("api_key", apiKey).apply();
        }
    }

    public String getApiKey() {
        return prefs.getString("api_key", null);
    }
}
