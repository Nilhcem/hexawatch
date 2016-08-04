package com.nilhcem.hexawatch.ui.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.ui.BaseFragment;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class CustomFragment extends BaseFragment {

    @BindView(R.id.custom_hexawatch) HexawatchView hexawatch;
    @BindView(R.id.custom_bg_color) ColorChooserView bgColorChooser;
    @BindView(R.id.custom_fill_color) ColorChooserView fillColorChooser;
    @BindView(R.id.custom_stroke_color) ColorChooserView strokeColorChooser;
    @BindView(R.id.custom_stroke_size) SeekBarChooserView strokeSizeChooser;
    @BindView(R.id.custom_hexagon_ratio) SeekBarChooserView hexagonRatioChooser;

    private WatchTheme theme = WatchTheme.Preset.CUSTOM.theme;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hexawatch.setTheme(theme);

        bgColorChooser.setColor(theme.bgColor);
        bgColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(color, theme.fillColor, theme.strokeColor, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        fillColorChooser.setColor(theme.fillColor);
        fillColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(theme.bgColor, color, theme.strokeColor, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        strokeColorChooser.setColor(theme.strokeColor);
        strokeColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, color, theme.strokeWidthDp, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        strokeSizeChooser.setValue(12);
        strokeSizeChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, theme.strokeColor, 1f + 0.04f * value, theme.innerHexaRatio);
                hexawatch.setTheme(theme);
            }
        });

        hexagonRatioChooser.setValue(82);
        hexagonRatioChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                theme = new WatchTheme(theme.bgColor, theme.fillColor, theme.strokeColor, theme.strokeWidthDp, 0.1f + 0.008f * value);
                hexawatch.setTheme(theme);
            }
        });
    }
}
