package com.nilhcem.hexawatch.ui.listing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

public class PresetsListViewHolder extends RecyclerView.ViewHolder {

    private HexawatchView watchface;
    private TextView name;

    public PresetsListViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.presets_list_item, parent, false));
        watchface = (HexawatchView) itemView.findViewById(R.id.watchface);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void bindData(ColorPreset colorPreset) {
        watchface.setColor(colorPreset);
        name.setText(colorPreset.nameRes);
    }
}
