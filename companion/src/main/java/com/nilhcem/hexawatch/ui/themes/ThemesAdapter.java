package com.nilhcem.hexawatch.ui.themes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.WatchTheme;

class ThemesAdapter extends RecyclerView.Adapter<ThemesViewHolder> {

    interface OnThemePresetSelectedListener {
        void onThemePresetSelected(WatchTheme.Preset preset);
    }

    private WatchTheme customTheme;
    private WatchTheme.Preset selectedPreset;
    private final OnThemePresetSelectedListener listener;

    ThemesAdapter(WatchTheme customTheme, WatchTheme.Preset selectedPreset, OnThemePresetSelectedListener listener) {
        this.customTheme = customTheme;
        this.selectedPreset = selectedPreset;
        this.listener = listener;
    }

    @Override
    public ThemesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ThemesViewHolder holder, int position) {
        final WatchTheme.Preset themePreset = WatchTheme.Preset.values()[position];
        WatchTheme theme = themePreset == WatchTheme.Preset.CUSTOM ? customTheme : themePreset.theme;
        holder.bindData(themePreset.nameRes, theme, themePreset == selectedPreset);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPreset = themePreset;
                notifyDataSetChanged();
                listener.onThemePresetSelected(themePreset);
            }
        });
    }

    @Override
    public int getItemCount() {
        return WatchTheme.Preset.values().length;
    }

    void updateTheme(WatchTheme.Preset selectedPreset, WatchTheme customTheme) {
        if (this.customTheme != customTheme || this.selectedPreset != selectedPreset) {
            this.customTheme = customTheme;
            this.selectedPreset = selectedPreset;
            notifyDataSetChanged();
        }
    }
}
