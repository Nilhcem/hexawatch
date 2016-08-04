package com.nilhcem.hexawatch.ui.themes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.WatchTheme;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesViewHolder> {

    private final WatchTheme customTheme;

    public ThemesAdapter(WatchTheme customTheme) {
        this.customTheme = customTheme;
    }

    @Override
    public ThemesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ThemesViewHolder holder, int position) {
        WatchTheme.Preset themePreset = WatchTheme.Preset.values()[position];
        WatchTheme theme = themePreset == WatchTheme.Preset.CUSTOM ? customTheme : themePreset.theme;
        holder.bindData(themePreset.nameRes, theme);
    }

    @Override
    public int getItemCount() {
        return WatchTheme.Preset.values().length;
    }
}
