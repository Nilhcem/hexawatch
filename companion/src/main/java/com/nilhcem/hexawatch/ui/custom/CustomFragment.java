package com.nilhcem.hexawatch.ui.custom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.ui.BaseFragment;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomFragment extends BaseFragment {

    @BindView(R.id.custom_layout) ViewGroup rootView;
    @BindView(R.id.custom_hexawatch) HexawatchView hexawatch;
    @BindView(R.id.custom_bg_color) ColorChooserView bgColorChooser;
    @BindView(R.id.custom_fill_color) ColorChooserView fillColorChooser;
    @BindView(R.id.custom_stroke_color) ColorChooserView strokeColorChooser;
    @BindView(R.id.custom_stroke_size) SeekBarChooserView strokeSizeChooser;
    @BindView(R.id.custom_hexagon_ratio) SeekBarChooserView hexagonRatioChooser;

    private WatchTheme theme;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        theme = configHelper.getCustomTheme();
        setControlValues(theme);

        bgColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(color, theme.fillColor, theme.strokeColor, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        fillColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(theme.bgColor, color, theme.strokeColor, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        strokeColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, color, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        strokeSizeChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, theme.strokeColor, 1f + 0.04f * value, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        hexagonRatioChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, theme.strokeColor, theme.strokeWidthDp, 0.1f + 0.008f * value);
                hexawatch.setTheme(theme);
            }
        });
    }

    @OnClick(R.id.custom_buttons_reset)
    public void resetCustomTheme() {
        theme = WatchTheme.Preset.CUSTOM.theme;
        configHelper.setCustomTheme(theme);
        setControlValues(theme);
        snackbar(R.string.custom_reset_msg);
    }

    @OnClick(R.id.custom_buttons_save)
    public void saveCustomTheme() {
        configHelper.setCustomTheme(theme);
        hexawatch.setTheme(theme);
        snackbar(R.string.custom_save_msg);
    }

    private void setControlValues(WatchTheme theme) {
        bgColorChooser.setColor(theme.bgColor);
        fillColorChooser.setColor(theme.fillColor);
        strokeColorChooser.setColor(theme.strokeColor);
        strokeSizeChooser.setValue(getValueInRange((theme.strokeWidthDp - 1f) / 0.04f));
        hexagonRatioChooser.setValue(getValueInRange((theme.innerHexaRatio - 0.1f) / 0.008f));
        hexawatch.setTheme(theme);
    }

    private void snackbar(@StringRes int resId) {
        Snackbar snack = Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }

    private int getValueInRange(float value) {
        return Math.max(0, Math.min(100, Math.round(value)));
    }
}
