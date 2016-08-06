package com.nilhcem.hexawatch.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfig {

    public static final String KEY_IS_CIRCLE = "isCircle";

    private final SharedPreferences prefs;

    public AppConfig(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void togglePreviewShape() {
        prefs.edit().putBoolean(KEY_IS_CIRCLE, !isPreviewCircle()).apply();
    }

    public boolean isPreviewCircle() {
        return prefs.getBoolean(KEY_IS_CIRCLE, true);
    }
}
