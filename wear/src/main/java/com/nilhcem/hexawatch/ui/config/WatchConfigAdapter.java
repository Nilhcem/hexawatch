package com.nilhcem.hexawatch.ui.config;

import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.WatchTheme;

public class WatchConfigAdapter extends WearableListView.Adapter {

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WearableListView.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(WatchConfigEntryView.LAYOUT_RES, parent, false));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        WatchTheme.Preset themePreset = WatchTheme.Preset.values()[position];
        ((WatchConfigEntryView) holder.itemView).bindData(themePreset.nameRes, themePreset.theme);
    }

    @Override
    public int getItemCount() {
        return WatchTheme.Preset.values().length;
    }
}
