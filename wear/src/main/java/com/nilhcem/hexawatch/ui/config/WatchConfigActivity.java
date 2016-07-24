package com.nilhcem.hexawatch.ui.config;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.core.config.ConfigHelper;

public class WatchConfigActivity extends Activity {

    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_color);
        header = (TextView) findViewById(R.id.config_header);
        WearableListView list = (WearableListView) findViewById(R.id.config_list);
        list.setAdapter(new WatchConfigColorAdapter());

        // List item selection
        list.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                ConfigHelper.INSTANCE.setColorPreset(WatchConfigActivity.this, ColorPreset.values()[viewHolder.getLayoutPosition()]);
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
    }
}
