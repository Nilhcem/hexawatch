package com.nilhcem.hexawatch.ui.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.ui.BaseActivity;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {

    @BindView(R.id.custom_hexawatch) HexawatchView hexawatch;
    @BindView(R.id.custom_bg_color) ColorChooserView bgColorChooser;
    @BindView(R.id.custom_fill_color) ColorChooserView fillColorChooser;
    @BindView(R.id.custom_stroke_color) ColorChooserView strokeColorChooser;
    @BindView(R.id.custom_stroke_size) SeekBarChooserView strokeSizeChooser;
    @BindView(R.id.custom_hexagon_ratio) SeekBarChooserView hexagonRatioChooser;

    private int bgColor = ColorPreset.PINK.bgColor;
    private int fillColor = ColorPreset.PINK.fillColor;
    private int strokeColor = ColorPreset.PINK.strokeColor;

    private float strokeSize = 1.5f;
    private float hexagonRatio = 0.75f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        hexawatch.setColor(ColorPreset.PINK);

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
                hexawatch.setStrokeWidth(ContextUtils.dpToPx(CustomActivity.this, strokeSize));
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
        hexawatch.setColor(bgColor, strokeColor, fillColor);
    }
}
