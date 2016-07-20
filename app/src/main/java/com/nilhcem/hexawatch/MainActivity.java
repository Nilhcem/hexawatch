package com.nilhcem.hexawatch;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
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
import com.nilhcem.hexawatch.common.ui.painter.Painter;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

import static com.nilhcem.hexawatch.common.ui.Hexawatch.UNIT_DP;

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

        public HexaTestView(Context context) {
            super(context);
            Painter painter = new Painter(context);
            hexawatch = new Hexawatch.Builder(context).shape(WatchShape.CIRCLE).size(300, UNIT_DP).strokeWidth(1.5f, Hexawatch.UNIT_DP).marginWidth(60, Hexawatch.UNIT_PX).painter(painter).build();

            painter.setColor(ColorPreset.BLACK);
//            painter.setMode(WatchMode.AMBIENT);
//            painter.setMode(WatchMode.LOW_BIT);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            hexawatch.drawTime(canvas, 3, 42);
        }
    }
}
