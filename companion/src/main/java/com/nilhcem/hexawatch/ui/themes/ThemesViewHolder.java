package com.nilhcem.hexawatch.ui.themes;

import android.support.annotation.StringRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.ui.BaseViewHolder;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class ThemesViewHolder extends BaseViewHolder {

    @BindView(R.id.watchface) HexawatchView watchface;
    @BindView(R.id.name) TextView name;

    public ThemesViewHolder(ViewGroup parent) {
        super(parent, R.layout.presets_item);
    }

    public void bindData(@StringRes int nameRes, WatchTheme theme) {
        watchface.setTheme(theme);
        name.setText(nameRes);
    }
}
