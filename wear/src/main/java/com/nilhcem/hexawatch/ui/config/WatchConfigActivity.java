package com.nilhcem.hexawatch.ui.config;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.config.ConfigHelper;
import com.nilhcem.hexawatch.common.core.WatchTheme;

public class WatchConfigActivity extends Activity {

    private TextView header;
    private ConfigHelper configHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configHelper = new ConfigHelper(this);

        setContentView(R.layout.watch_config);
        header = (TextView) findViewById(R.id.config_header);
        WearableListView list = (WearableListView) findViewById(R.id.config_list);
        list.setAdapter(new WatchConfigAdapter(configHelper.getCustomTheme()));

        // List item selection
        list.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                WatchTheme.Preset preset = WatchTheme.Preset.values()[viewHolder.getLayoutPosition()];
                configHelper.setTheme(preset);
                finish();
            }

            @Override
            public void onTopEmptyRegionClick() {
            }
        });

        // Move header on list scroll
        list.addOnScrollListener(new WearableListView.OnScrollListener() {
            @Override
            public void onAbsoluteScrollChange(int i) {
                if (i > 0) {
                    header.setY(-i);
                }
            }

            @Override
            public void onScroll(int i) {
            }

            @Override
            public void onScrollStateChanged(int i) {
            }

            @Override
            public void onCentralPositionChanged(int i) {
            }
        });

        // Automatically select current theme
        WatchTheme.Preset themePreset = configHelper.getThemePreset();
        list.scrollToPosition(themePreset.ordinal());
    }
}
