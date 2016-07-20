package com.nilhcem.hexawatch.ui.watchface;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import com.nilhcem.hexawatch.BuildConfig;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.core.watchface.TimeHelper;

import java.util.Calendar;

public abstract class BaseWatchFaceService extends CanvasWatchFaceService {

    @Override
    public CanvasWatchFaceService.Engine onCreateEngine() {
        CanvasWatchFaceService.Engine engine = super.onCreateEngine();
        if (BuildConfig.DEBUG && !(engine instanceof Engine)) {
            throw new IllegalStateException("Engine must be an instance of BaseWatchFaceService.Engine");
        }
        return engine;
    }

    public abstract class Engine extends CanvasWatchFaceService.Engine {

        private TimeHelper timeHelper;

        private boolean ambient;
        private boolean lowBitAmbient;
        private boolean isRound;
        private int chinSize;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            timeHelper = new TimeHelper(BaseWatchFaceService.this, this);
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);

            if (ambient != inAmbientMode) {
                ambient = inAmbientMode;
                onWatchModeChanged(getCurrentWatchMode());
                invalidate();
            }
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
            isRound = insets.isRound();
            chinSize = insets.getSystemWindowInsetBottom();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            super.onDraw(canvas, bounds);
            timeHelper.setTimeToNow();
            onDrawTime(canvas, bounds, isInAmbientMode(), timeHelper.getCalendar());
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            lowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            timeHelper.onVisibilityChanged(visible);
        }

        private WatchMode getCurrentWatchMode() {
            WatchMode watchMode;
            if (ambient) {
                if (lowBitAmbient) {
                    watchMode = WatchMode.LOW_BIT;
                } else {
                    watchMode = WatchMode.AMBIENT;
                }
            } else {
                watchMode = WatchMode.INTERACTIVE;
            }
            return watchMode;
        }

        protected abstract void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar);

        protected abstract void onWatchModeChanged(WatchMode mode);
    }
}
