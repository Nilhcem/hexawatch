package com.nilhcem.hexawatch.ui.presets;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.ui.BaseViewHolder;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class PresetsViewHolder extends BaseViewHolder {

    @BindView(R.id.watchface) HexawatchView watchface;
    @BindView(R.id.name) TextView name;

    public PresetsViewHolder(ViewGroup parent) {
        super(parent, R.layout.presets_item);
    }

    public void bindData(ColorPreset colorPreset) {
        watchface.setColor(colorPreset);
        name.setText(colorPreset.nameRes);
    }
}
