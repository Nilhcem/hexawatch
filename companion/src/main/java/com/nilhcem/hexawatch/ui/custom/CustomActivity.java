package com.nilhcem.hexawatch.ui.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        HexawatchView hexawatch = (HexawatchView) findViewById(R.id.custom_inner);
        hexawatch.setColor(ColorPreset.PINK);
    }
}
