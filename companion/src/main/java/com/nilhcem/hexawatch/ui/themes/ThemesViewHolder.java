package com.nilhcem.hexawatch.ui.themes;

import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.ui.BaseViewHolder;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class ThemesViewHolder extends BaseViewHolder {

    @BindView(R.id.theme_item_watchface) HexawatchView watchface;
    @BindView(R.id.theme_item_name) TextView name;

    public ThemesViewHolder(ViewGroup parent) {
        super(parent, R.layout.themes_item);
    }

    public void bindData(@StringRes int nameRes, WatchTheme theme, boolean isCircle, boolean selected) {
        watchface.setTheme(theme);
        watchface.setCircleShape(isCircle);
        name.setText(nameRes);
        itemView.setSelected(selected);
        ViewCompat.setElevation(itemView, selected ? ContextUtils.dpToPx(itemView.getContext(), 4f) : 0);
    }
}
