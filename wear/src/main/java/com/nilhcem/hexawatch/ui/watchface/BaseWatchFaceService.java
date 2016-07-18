package com.nilhcem.hexawatch.ui.watchface;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;

import com.nilhcem.hexawatch.BuildConfig;
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

        /**
         * Whether the display supports fewer bits for each color in ambient mode. When true, we
         * disable anti-aliasing in ambient mode.
         */
        private boolean lowBitAmbient;

        private boolean burnInProtection;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            /* initialize your watch face */
            timeHelper = new TimeHelper(BaseWatchFaceService.this, this);
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
            /* the wearable switched between modes */
            if (ambient != inAmbientMode) {
                ambient = inAmbientMode;
                if (lowBitAmbient) {
//                    mHandPaint.setAntiAlias(!inAmbientMode);
                }
                invalidate();
            }
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
            /* get device features (burn-in, low-bit ambient) */
            lowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
            burnInProtection = properties.getBoolean(PROPERTY_BURN_IN_PROTECTION, false);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            /* the watch face became visible or invisible */
            timeHelper.onVisibilityChanged(visible);
        }

        protected abstract void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar);
    }
}
