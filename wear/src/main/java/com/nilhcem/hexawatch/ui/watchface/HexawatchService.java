package com.nilhcem.hexawatch.ui.watchface;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.painter.Painter;

import java.util.Calendar;

import static com.nilhcem.hexawatch.common.ui.Hexawatch.UNIT_PX;

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
            painter = new Painter(HexawatchService.this);
            painter.setColor(ColorPreset.BLACK);

            WatchShape shape = getResources().getConfiguration().isScreenRound() ? WatchShape.CIRCLE : WatchShape.SQUARE;
            hexawatch = new Hexawatch.Builder(HexawatchService.this).shape(shape).size(400, UNIT_PX).strokeWidth(1.5f, Hexawatch.UNIT_DP).paddingWidth(BURN_IN_PADDING, Hexawatch.UNIT_PX).painter(painter).build();

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
