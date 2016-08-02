package com.nilhcem.hexawatch.ui.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.ui.BaseActivity;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import butterknife.BindView;

public class CustomActivity extends BaseActivity {

    @BindView(R.id.custom_inner) HexawatchView hexawatch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        hexawatch.setColor(ColorPreset.PINK);
    }
}
