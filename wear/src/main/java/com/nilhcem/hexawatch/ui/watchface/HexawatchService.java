package com.nilhcem.hexawatch.ui.watchface;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.Gravity;
import android.view.SurfaceHolder;

import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.core.theme.Theme;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.Painter;
import com.nilhcem.hexawatch.common.ui.PathGenerator;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.core.config.ConfigHelper;

import java.util.Calendar;

public class HexawatchService extends BaseWatchFaceService {

    private static final int BURN_IN_PADDING = 10;

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends BaseWatchFaceService.Engine implements SharedPreferences.OnSharedPreferenceChangeListener {

        private Hexawatch hexawatch;
        private PathGenerator pathGenerator;
        private Painter painter;
        private Theme theme;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            ConfigHelper.INSTANCE.getSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);

            theme = ConfigHelper.INSTANCE.getPreset(context).theme;
            pathGenerator = new PathGenerator(context);
            pathGenerator.setInnerHexaRatio(theme.innerHexaRatio);
            painter = new Painter(context);
            painter.setColors(theme.bgColor, theme.strokeColor, theme.fillColor);
            hexawatch = new Hexawatch(painter, pathGenerator);

            setWatchFaceStyle(new WatchFaceStyle.Builder(HexawatchService.this)
                    .setAcceptsTapEvents(false)
                    .setAmbientPeekMode(WatchFaceStyle.AMBIENT_PEEK_MODE_VISIBLE)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setHotwordIndicatorGravity(Gravity.TOP)
                    .setPeekOpacityMode(WatchFaceStyle.PEEK_OPACITY_MODE_OPAQUE)
                    .setShowSystemUiTime(false)
                    .setShowUnreadCountIndicator(true)
                    .setStatusBarGravity(Gravity.TOP)
                    .setViewProtectionMode(WatchFaceStyle.PROTECT_STATUS_BAR | WatchFaceStyle.PROTECT_HOTWORD_INDICATOR)
                    .build());
        }

        @Override
        protected void onShapeChanged(WatchShape shape) {
            pathGenerator.setShape(shape);
        }

        @Override
        protected void onBurnInProtectionChanged(boolean burnInProtection) {
            int strokeWidth = ContextUtils.dpToPx(context, theme.strokeWidthDp);
            hexawatch.setDimensions(width, height, burnInProtection ? BURN_IN_PADDING : 0, strokeWidth);
            invalidate();
        }

        @Override
        protected void onWatchModeChanged(WatchMode mode) {
            hexawatch.setMode(mode);
        }

        @Override
        protected void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar) {
            hexawatch.drawTime(canvas, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
        }

        @Override
        public void onDestroy() {
            ConfigHelper.INSTANCE.getSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(this);
            super.onDestroy();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Theme theme = ConfigHelper.INSTANCE.getPreset(context).theme;
            painter.setColors(theme.bgColor, theme.strokeColor, theme.fillColor);
            int strokeWidth = ContextUtils.dpToPx(context, theme.strokeWidthDp);
            hexawatch.setDimensions(width, height, burnInProtection != null && burnInProtection ? BURN_IN_PADDING : 0, strokeWidth);
            pathGenerator.setInnerHexaRatio(theme.innerHexaRatio);
            invalidate();
        }
    }
}
