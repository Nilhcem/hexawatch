package com.nilhcem.hexawatch.ui.watchface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;
import android.view.WindowInsets;
import android.view.WindowManager;

import com.nilhcem.hexawatch.BuildConfig;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.core.TimeHelper;

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
        private Boolean burnInProtection;
        private WatchShape shape;

        protected Context context;
        protected int width;
        protected int height;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            context = BaseWatchFaceService.this;
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

            Point screenSize = new Point();
            int chinSize = insets.getSystemWindowInsetBottom();
            ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(screenSize);
            width = screenSize.x;
            height = screenSize.y + chinSize;

            WatchShape newShape = insets.isRound() ? WatchShape.CIRCLE : WatchShape.SQUARE;
            if (!newShape.equals(shape)) {
                shape = newShape;
                onShapeChanged(newShape);
            }
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            super.onDraw(canvas, bounds);

            // Only draw if watch is fully initialized.
            if (burnInProtection != null) {
                timeHelper.setTimeToNow();
                onDrawTime(canvas, bounds, timeHelper.getCalendar());
            }
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            lowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
            boolean newBurnInProtection = properties.getBoolean(PROPERTY_BURN_IN_PROTECTION, false);

            if (burnInProtection == null || burnInProtection != newBurnInProtection) {
                burnInProtection = newBurnInProtection;
                onBurnInProtectionChanged(newBurnInProtection);
            }
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

        protected abstract void onShapeChanged(WatchShape shape);

        protected abstract void onBurnInProtectionChanged(boolean burnInProtection);

        protected abstract void onDrawTime(Canvas canvas, Rect bounds, Calendar calendar);

        protected abstract void onWatchModeChanged(WatchMode mode);
    }
}
