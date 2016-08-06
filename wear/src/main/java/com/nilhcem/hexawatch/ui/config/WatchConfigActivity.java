package com.nilhcem.hexawatch.ui.config;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.View;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.config.SharedConfig;
import com.nilhcem.hexawatch.common.core.WatchTheme;

public class WatchConfigActivity extends Activity {

    private SharedConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config = new SharedConfig(this);

        setContentView(R.layout.watch_config);
        WearableListView list = (WearableListView) findViewById(R.id.config_list);
        list.setAdapter(new WatchConfigAdapter(config.getCustomTheme()));

        // List item selection
        list.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                WatchTheme.Preset preset = WatchTheme.Preset.values()[viewHolder.getLayoutPosition()];
                config.setThemePreset(preset);
                finish();
            }

            @Override
            public void onTopEmptyRegionClick() {
                // Do nothing
            }
        });

        // Move header on list scroll
        list.addOnScrollListener(new ScrollHeaderListener(findViewById(R.id.config_header)));

        // Automatically select current theme
        WatchTheme.Preset themePreset = config.getThemePreset();
        list.scrollToPosition(themePreset.ordinal());
    }

    static class ScrollHeaderListener implements WearableListView.OnScrollListener {

        private final View header;

        public ScrollHeaderListener(View header) {
            this.header = header;
        }

        @Override
        public void onAbsoluteScrollChange(int i) {
            if (i > 0) {
                header.setY(-i);
            }
        }

        @Override
        public void onScroll(int i) {
            // Do nothing
        }

        @Override
        public void onScrollStateChanged(int i) {
            // Do nothing
        }

        @Override
        public void onCentralPositionChanged(int i) {
            // Do nothing
        }
    }
}
