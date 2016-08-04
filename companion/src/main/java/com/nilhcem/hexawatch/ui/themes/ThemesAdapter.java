package com.nilhcem.hexawatch.ui.themes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.WatchTheme;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesViewHolder> {

    @Override
    public ThemesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ThemesViewHolder holder, int position) {
        WatchTheme.Preset themePreset = WatchTheme.Preset.values()[position];
        holder.bindData(themePreset.nameRes, themePreset.theme);
    }

    @Override
    public int getItemCount() {
        return WatchTheme.Preset.values().length;
    }
}
