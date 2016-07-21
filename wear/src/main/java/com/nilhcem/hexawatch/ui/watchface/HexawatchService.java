package com.nilhcem.hexawatch.ui.watchface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

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

        private Painter painter;
        private Hexawatch hexawatch;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            Context context = HexawatchService.this;
            painter = new Painter(context, ColorPreset.BLACK);

            WatchShape shape = getResources().getConfiguration().isScreenRound() ? WatchShape.CIRCLE : WatchShape.SQUARE;

            PathGenerator pathGenerator = new PathGenerator(context, 400);
            pathGenerator.setShape(shape);

            hexawatch = new Hexawatch(painter, pathGenerator);
            hexawatch.setWidths(ContextUtils.dpToPx(context, 1.5f), BURN_IN_PADDING);

//            setWatchFaceStyle(new WatchFaceStyle.Builder(MyWatchFace.this)
//                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
//                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
//                    .setShowSystemUiTime(false)
//                    .build());
        }

        @Override
        protected void onWatchModeChanged(WatchMode mode) {
            painter.setMode(mode);
        }

        @Override
        protected void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar) {
            hexawatch.drawTime(canvas, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
        }
    }
}
