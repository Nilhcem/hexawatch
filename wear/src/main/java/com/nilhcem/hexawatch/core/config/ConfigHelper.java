package com.nilhcem.hexawatch.core.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.nilhcem.hexawatch.common.core.Preset;

public enum ConfigHelper {
    INSTANCE;

    private static final String PREFS_NAME = "config";
    private static final String KEY_PRESET = "preset";

    public Preset getPreset(Context context) {
        return Preset.fromName(getSharedPreferences(context).getString(KEY_PRESET, null));
    }

    public void setPreset(Context context, Preset preset) {
        getSharedPreferences(context).edit().putString(KEY_PRESET, preset.name()).apply();
    }

    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
