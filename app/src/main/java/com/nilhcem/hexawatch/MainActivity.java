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

import com.nilhcem.hexawatch.common.Hexawatch;

import static com.nilhcem.hexawatch.common.Hexawatch.SHAPE_CIRCLE;
import static com.nilhcem.hexawatch.common.Hexawatch.UNIT_DP;

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

            // colored
            hexawatch = new Hexawatch.Builder(context).shape(SHAPE_CIRCLE).size(300, UNIT_DP).colorPreset(Hexawatch.ColorPreset.BLACK).build();

//            // ambient
//            hexawatch = new Hexawatch.Builder(context).shape(SHAPE_CIRCLE).size(300, UNIT_DP).ambient().build();
//
//            // low-bit ambient
//            hexawatch = new Hexawatch.Builder(context).shape(SHAPE_CIRCLE).size(300, UNIT_DP).lowBitAmbient().build();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            hexawatch.drawTime(canvas, 3, 42);
        }
    }
}
