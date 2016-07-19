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

            // black
            hexawatch = new Hexawatch.Builder(context).shape(Hexawatch.Shape.CIRCLE).size(300, Hexawatch.Unit.DP).bgColor(0xff333333).strokeColor(0xffe6e6e6).fillColor(0xffb9b9b9).build();
//
//            // pink
//            hexawatch = new Hexawatch.Builder(context).shape(Hexawatch.Shape.CIRCLE).size(300, Hexawatch.Unit.DP).bgColor(0xff952261).strokeColor(0xfff1e5eb).fillColor(0xffcd79a6).build();
//
//            // blue
//            hexawatch = new Hexawatch.Builder(context).shape(Hexawatch.Shape.CIRCLE).size(300, Hexawatch.Unit.DP).bgColor(0xff387b94).strokeColor(0xffebddd4).fillColor(0xff1f6179).build();
//
//            // ambient
//            hexawatch = new Hexawatch.Builder(context).shape(Hexawatch.Shape.CIRCLE).size(300, Hexawatch.Unit.DP).ambient().build();
//
//            // low-bit ambient
//            hexawatch = new Hexawatch.Builder(context).shape(Hexawatch.Shape.CIRCLE).size(300, Hexawatch.Unit.DP).lowBitAmbient().build();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            hexawatch.drawTime(canvas, 3, 42);
        }
    }
}
