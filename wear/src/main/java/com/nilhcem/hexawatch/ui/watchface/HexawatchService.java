package com.nilhcem.hexawatch.ui.watchface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.nilhcem.hexawatch.common.Hexawatch;
import com.nilhcem.hexawatch.common.HexawatchCircleDrawer;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

import java.util.Calendar;

import static com.nilhcem.hexawatch.common.Hexawatch.SHAPE_CIRCLE;
import static com.nilhcem.hexawatch.common.Hexawatch.UNIT_DP;
import static com.nilhcem.hexawatch.common.Hexawatch.UNIT_PX;

public class HexawatchService extends BaseWatchFaceService {

    private static final int BURN_IN_MARGIN = 10;

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends BaseWatchFaceService.Engine {

        private Hexawatch hexaWatch;
        private Hexawatch ambiantWatch;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
//            getResources().getConfiguration().isScreenRound()

            hexaWatch = new Hexawatch.Builder(HexawatchService.this).shape(SHAPE_CIRCLE).size(400, UNIT_PX).marginWidth(BURN_IN_MARGIN, UNIT_PX).colorPreset(Hexawatch.ColorPreset.BLACK).build();
            ambiantWatch = new Hexawatch.Builder(HexawatchService.this).shape(SHAPE_CIRCLE).size(400, UNIT_PX).marginWidth(BURN_IN_MARGIN, UNIT_PX).ambient().build();

//            setWatchFaceStyle(new WatchFaceStyle.Builder(MyWatchFace.this)
//                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
//                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
//                    .setShowSystemUiTime(false)
//                    .build());
        }

        @Override
        protected void onDrawTime(Canvas canvas, Rect bounds, boolean ambiant, Calendar calendar) {
            Hexawatch watch = ambiant ? ambiantWatch : hexaWatch;
            watch.drawTime(canvas, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
        }
    }
}
