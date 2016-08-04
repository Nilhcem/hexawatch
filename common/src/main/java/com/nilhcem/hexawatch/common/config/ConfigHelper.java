package com.nilhcem.hexawatch.common.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.nilhcem.hexawatch.common.core.WatchTheme;

public class ConfigHelper {

    private static final String KEY_PRESET_NAME = "preset";
    private static final String KEY_CUSTOM_BG_COLOR = "custom_bg";
    private static final String KEY_CUSTOM_FILL_COLOR = "custom_fill";
    private static final String KEY_CUSTOM_STROKE_COLOR = "custom_stroke";
    private static final String KEY_CUSTOM_STROKE_WIDTH = "custom_width";
    private static final String KEY_CUSTOM_INNER_HEXA_RATIO = "custom_ratio";

    private final SharedPreferences prefs;

    public ConfigHelper(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public WatchTheme.Preset getThemePreset() {
        return WatchTheme.Preset.fromName(prefs.getString(KEY_PRESET_NAME, null));
    }

    public WatchTheme getTheme() {
        WatchTheme.Preset themePreset = getThemePreset();
        if (themePreset == WatchTheme.Preset.CUSTOM) {
            return getCustomTheme();
        } else {
            return themePreset.theme;
        }
    }

    public void setTheme(@NonNull WatchTheme.Preset themePreset) {
        prefs.edit().putString(KEY_PRESET_NAME, themePreset.name()).apply();
    }

    public void setCustomTheme(@NonNull WatchTheme theme) {
        prefs.edit()
                .putString(KEY_PRESET_NAME, WatchTheme.Preset.CUSTOM.name())
                .putInt(KEY_CUSTOM_BG_COLOR, theme.bgColor)
                .putInt(KEY_CUSTOM_FILL_COLOR, theme.fillColor)
                .putInt(KEY_CUSTOM_STROKE_COLOR, theme.strokeColor)
                .putFloat(KEY_CUSTOM_STROKE_WIDTH, theme.strokeWidthDp)
                .putFloat(KEY_CUSTOM_INNER_HEXA_RATIO, theme.innerHexaRatio)
                .apply();
    }

    private WatchTheme getCustomTheme() {
        WatchTheme custom = WatchTheme.Preset.CUSTOM.theme;
        return new WatchTheme(
                prefs.getInt(KEY_CUSTOM_BG_COLOR, custom.bgColor),
                prefs.getInt(KEY_CUSTOM_FILL_COLOR, custom.fillColor),
                prefs.getInt(KEY_CUSTOM_STROKE_COLOR, custom.strokeColor),
                prefs.getFloat(KEY_CUSTOM_STROKE_WIDTH, custom.strokeWidthDp),
                prefs.getFloat(KEY_CUSTOM_INNER_HEXA_RATIO, custom.innerHexaRatio)
        );
    }
}
