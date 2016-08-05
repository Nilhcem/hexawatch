package com.nilhcem.hexawatch.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.common.ui.Hexawatch;

public class HexawatchView extends View {

    private final Hexawatch hexawatch;
    private final int padding;

    private int hour = 10;
    private int minute = 10;

    public HexawatchView(Context context) {
        this(context, null);
    }

    public HexawatchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HexawatchView);
        boolean isRound = a.getBoolean(R.styleable.HexawatchView_is_round, true);
        padding = a.getDimensionPixelSize(R.styleable.HexawatchView_android_padding, 0);
        a.recycle();

        hexawatch = new Hexawatch(context);
        hexawatch.setShape(isRound ? WatchShape.CIRCLE : WatchShape.SQUARE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight(), padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hexawatch.drawTime(canvas, hour % 12, minute);
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        invalidate();
    }

    public void setTheme(WatchTheme theme) {
        hexawatch.setTheme(theme);
        invalidate();
    }
}
