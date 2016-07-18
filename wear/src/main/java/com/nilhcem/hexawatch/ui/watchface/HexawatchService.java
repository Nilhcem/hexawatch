package com.nilhcem.hexawatch.ui.watchface;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.nilhcem.hexawatch.common.Hexawatch;
import com.nilhcem.hexawatch.common.HexawatchCircleDrawer;

import java.util.Calendar;

public class HexawatchService extends BaseWatchFaceService {

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
            hexaWatch = new HexawatchCircleDrawer(HexawatchService.this, 400, 400, 2f, 0xff333333, 0xffe6e6e6, 0xffb9b9b9);
            ambiantWatch = new HexawatchCircleDrawer(HexawatchService.this, 400, 400, 1f, 0xff000000, 0xffdddddd, 0xffdddddd);

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