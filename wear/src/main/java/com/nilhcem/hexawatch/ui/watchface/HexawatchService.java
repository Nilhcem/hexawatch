package com.nilhcem.hexawatch.ui.watchface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.Painter;
import com.nilhcem.hexawatch.common.ui.PathGenerator;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

import java.util.Calendar;

public class HexawatchService extends BaseWatchFaceService {

    private static final int BURN_IN_PADDING = 10;

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends BaseWatchFaceService.Engine {

        private Hexawatch hexawatch;
        private PathGenerator pathGenerator;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            Context context = HexawatchService.this;

            pathGenerator = new PathGenerator(context, 400);
            hexawatch = new Hexawatch(new Painter(context, ColorPreset.BLACK), pathGenerator);

//            setWatchFaceStyle(new WatchFaceStyle.Builder(MyWatchFace.this)
//                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
//                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
//                    .setShowSystemUiTime(false)
//                    .build());
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
            boolean isRound = insets.isRound();
            int chinSize = insets.getSystemWindowInsetBottom();

//          getResources().getConfiguration().isScreenRound()
            pathGenerator.setShape(isRound ? WatchShape.CIRCLE : WatchShape.SQUARE);
            hexawatch.setWidths(ContextUtils.dpToPx(HexawatchService.this, 1.4f), BURN_IN_PADDING + chinSize);
        }

        @Override
        protected void onWatchModeChanged(WatchMode mode) {
            hexawatch.setMode(mode);
        }

        @Override
        protected void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar) {
            hexawatch.drawTime(canvas, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
        }
    }
}
