package com.nilhcem.hexawatch.ui.listing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

public class PresetsListViewHolder extends RecyclerView.ViewHolder {

    private HexawatchView watchface;
    private TextView name;

    public PresetsListViewHolder(View itemView) {
        super(itemView);
        watchface = (HexawatchView) itemView.findViewById(R.id.watchface);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void bindData(ColorPreset colorPreset) {
        watchface.setColor(colorPreset);
        name.setText(colorPreset.name());
    }
}
