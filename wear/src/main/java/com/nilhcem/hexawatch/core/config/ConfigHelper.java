package com.nilhcem.hexawatch.core.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.nilhcem.hexawatch.common.core.WatchTheme;

public enum ConfigHelper {
    INSTANCE;

    private static final String PREFS_NAME = "config";
    private static final String KEY_PRESET = "preset";

    public WatchTheme.Preset getPreset(Context context) {
        return WatchTheme.Preset.fromName(getSharedPreferences(context).getString(KEY_PRESET, null));
    }

    public void setPreset(Context context, WatchTheme.Preset themePreset) {
        getSharedPreferences(context).edit().putString(KEY_PRESET, themePreset.name()).apply();
    }

    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
