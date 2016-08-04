package com.nilhcem.hexawatch.ui.themes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.theme.ThemePreset;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesViewHolder> {

    @Override
    public ThemesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ThemesViewHolder holder, int position) {
        holder.bindData(ThemePreset.values()[position]);
    }

    @Override
    public int getItemCount() {
        return ThemePreset.values().length;
    }
}
