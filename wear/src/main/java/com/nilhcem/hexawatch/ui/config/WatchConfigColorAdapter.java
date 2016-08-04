package com.nilhcem.hexawatch.ui.config;

import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.theme.ThemePreset;

public class WatchConfigColorAdapter extends WearableListView.Adapter {

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WearableListView.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(WatchConfigColorEntryView.LAYOUT_RES, parent, false));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ((WatchConfigColorEntryView) holder.itemView).bindData(ThemePreset.values()[position]);
    }

    @Override
    public int getItemCount() {
        return ThemePreset.values().length;
    }
}
