package com.nilhcem.hexawatch.ui.presets;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.common.core.Preset;

public class PresetsAdapter extends RecyclerView.Adapter<PresetsViewHolder> {

    @Override
    public PresetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PresetsViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PresetsViewHolder holder, int position) {
        holder.bindData(Preset.values()[position]);
    }

    @Override
    public int getItemCount() {
        return Preset.values().length;
    }
}
