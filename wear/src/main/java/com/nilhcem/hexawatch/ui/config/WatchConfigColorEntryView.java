package com.nilhcem.hexawatch.ui.config;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;

public class WatchConfigColorEntryView extends LinearLayout implements WearableListView.OnCenterProximityListener {

    public static final int LAYOUT_RES = R.layout.configuration_color_item;
    private static final int ANIM_DURATION = 200;

    private CircledImageView circle;
    private TextView name;

    public WatchConfigColorEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        circle = (CircledImageView) findViewById(R.id.config_item_circle);
        name = (TextView) findViewById(R.id.config_item_name);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        onPositionChanged(animate, true);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        onPositionChanged(animate, false);
    }

    public void bindData(@StringRes int nameRes, WatchTheme theme) {
        circle.setCircleColor(theme.bgColor);
        circle.setCircleBorderColor(theme.strokeColor);
        name.setText(nameRes);
        onPositionChanged(false, false);
    }

    private void onPositionChanged(boolean animate, boolean center) {
        float circleScale;
        float textAlpha;
        if (center) {
            circleScale = 1.0f;
            textAlpha = 1.0f;
        } else {
            circleScale = 0.88f;
            textAlpha = 0.5f;
        }

        if (animate) {
            circle.animate().scaleX(circleScale).scaleY(circleScale).setDuration(ANIM_DURATION);
        } else {
            circle.setScaleX(circleScale);
            circle.setScaleY(circleScale);
        }
        name.setAlpha(textAlpha);
    }
}
