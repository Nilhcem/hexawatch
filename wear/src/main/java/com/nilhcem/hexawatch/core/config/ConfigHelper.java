package com.nilhcem.hexawatch.core.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.nilhcem.hexawatch.common.core.ColorPreset;

public enum ConfigHelper {
    INSTANCE;

    private static final String PREFS_NAME = "config";
    private static final String KEY_PRESET = "preset";

    public ColorPreset getColorPreset(Context context) {
        return ColorPreset.fromName(getSharedPreferences(context).getString(KEY_PRESET, null));
    }

    public void setColorPreset(Context context, ColorPreset colorPreset) {
        getSharedPreferences(context).edit().putString(KEY_PRESET, colorPreset.name()).apply();
    }

    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
