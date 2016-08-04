package com.nilhcem.hexawatch.ui.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.theme.ThemePreset;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
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

    private int bgColor = ThemePreset.CUSTOM.theme.bgColor;
    private int fillColor = ThemePreset.CUSTOM.theme.fillColor;
    private int strokeColor = ThemePreset.CUSTOM.theme.strokeColor;

    private float strokeSize = 1.5f;
    private float hexagonRatio = 0.75f;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hexawatch.setColors(bgColor, strokeColor, fillColor);

        bgColorChooser.setColor(bgColor);
        bgColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                bgColor = color;
                updateHexawatchColors();
            }
        });

        fillColorChooser.setColor(fillColor);
        fillColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                fillColor = color;
                updateHexawatchColors();
            }
        });

        strokeColorChooser.setColor(strokeColor);
        strokeColorChooser.setOnColorSelectedListener(new ColorChooserView.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                strokeColor = color;
                updateHexawatchColors();
            }
        });

        strokeSizeChooser.setValue(12);
        strokeSizeChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                strokeSize = 1f + 0.04f * value;
                hexawatch.setStrokeWidth(ContextUtils.dpToPx(getContext(), strokeSize));
            }
        });

        hexagonRatioChooser.setValue(82);
        hexagonRatioChooser.setOnValueChangedListener(new SeekBarChooserView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                hexagonRatio = 0.1f + 0.008f * value;
                hexawatch.setInnerHexaRatio(hexagonRatio);
            }
        });
    }

    private void updateHexawatchColors() {
        hexawatch.setColors(bgColor, strokeColor, fillColor);
    }
}
