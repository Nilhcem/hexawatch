package com.nilhcem.hexawatch.ui.themes;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.theme.ThemePreset;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.ui.BaseViewHolder;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class ThemesViewHolder extends BaseViewHolder {

    @BindView(R.id.watchface) HexawatchView watchface;
    @BindView(R.id.name) TextView name;

    public ThemesViewHolder(ViewGroup parent) {
        super(parent, R.layout.presets_item);
    }

    public void bindData(ThemePreset preset) {
        watchface.setColors(preset.theme.bgColor, preset.theme.strokeColor, preset.theme.fillColor);
        watchface.setInnerHexaRatio(preset.theme.innerHexaRatio);
        watchface.setStrokeWidth(ContextUtils.dpToPx(itemView.getContext(), preset.theme.strokeWidthDp));
        name.setText(preset.nameRes);
    }
}
