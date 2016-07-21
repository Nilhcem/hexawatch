package com.nilhcem.hexawatch;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.Painter;
import com.nilhcem.hexawatch.common.ui.PathGenerator;
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

        private Hexawatch hexawatch;
        private PathGenerator pathGenerator;

        public HexaTestView(final Context context) {
            super(context);
            pathGenerator = new PathGenerator(context);
            pathGenerator.setShape(WatchShape.CIRCLE);

            final Painter painter = new Painter(context, ColorPreset.BLACK);
            hexawatch = new Hexawatch(painter, pathGenerator);

// Test data
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    painter.setColor(ColorPreset.PINK);
//                    invalidate();
//                }
//            }, 1000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    hexawatch.setMode(WatchMode.AMBIENT);
//                    invalidate();
//                }
//            }, 2000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    pathGenerator.setInnerHexaRatio(0.4f);
//                    pathGenerator.setShape(WatchShape.SQUARE);
//                    hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight() / 2, 160, ContextUtils.dpToPx(context, 4.5f));
//                    painter.setColor(ColorPreset.BLUE);
//                    hexawatch.setMode(WatchMode.INTERACTIVE);
//                    invalidate();
//                }
//            }, 3000);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight() / 2, 160, ContextUtils.dpToPx(context, 4.5f));
//                    hexawatch.setMode(WatchMode.LOW_BIT);
//                    invalidate();
//                }
//            }, 4000);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight(), 60, ContextUtils.dpToPx(getContext(), 1.5f));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            hexawatch.drawTime(canvas, 3, 42);
        }
    }
}
