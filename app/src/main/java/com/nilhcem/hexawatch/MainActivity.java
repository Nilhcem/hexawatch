package com.nilhcem.hexawatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nilhcem.hexawatch.common.HexawatchCircleDrawer;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout layout = new FrameLayout(this);
        HexaTestView view = new HexaTestView(this);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));

        layout.setBackgroundColor(0xff151515);
        layout.addView(view);
        setContentView(layout);
    }

    private class HexaTestView extends View {

        private HexawatchCircleDrawer drawer;

        public HexaTestView(Context context) {
            super(context);

//            // black
//            drawer = new HexawatchCircleDrawer(context, ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 2f), ContextUtils.dpToIntPx(context, 1f), 0xff333333, 0xffe6e6e6, 0xffb9b9b9, false, false);
//
//            // pink
//            drawer = new HexawatchCircleDrawer(context, ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 2f), ContextUtils.dpToIntPx(context, 1f), 0xff952261, 0xfff1e5eb, 0xffcd79a6, false, false);
//
//            // blue
            drawer = new HexawatchCircleDrawer(context, ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 2f), ContextUtils.dpToIntPx(context, 1f), 0xff387b94, 0xffebddd4, 0xff1f6179, false, false);
//
//            // ambient
//            drawer = new HexawatchCircleDrawer(context, ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 1f), 10, Color.TRANSPARENT, 0xff666666, 0xffdddddd, true, false);
//
//            // low-bit ambient
//            drawer = new HexawatchCircleDrawer(context, ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 300), ContextUtils.dpToIntPx(context, 1f), 10, Color.TRANSPARENT, Color.WHITE, Color.WHITE, true, true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawer.drawTime(canvas, 3, 42);
        }
    }
}
