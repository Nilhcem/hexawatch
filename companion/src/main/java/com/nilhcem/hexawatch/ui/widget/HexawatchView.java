package com.nilhcem.hexawatch.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.Painter;
import com.nilhcem.hexawatch.common.ui.PathGenerator;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

public class HexawatchView extends View {

    private final Hexawatch hexawatch;
    private final PathGenerator pathGenerator;
    private final Painter painter;
    private final int padding;

    public HexawatchView(Context context) {
        this(context, null);
    }

    public HexawatchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HexawatchView);
        boolean isRound = a.getBoolean(R.styleable.HexawatchView_is_round, true);
        padding = a.getDimensionPixelSize(R.styleable.HexawatchView_android_padding, 0);
        a.recycle();

        pathGenerator = new PathGenerator(context);
        pathGenerator.setShape(isRound ? WatchShape.CIRCLE : WatchShape.SQUARE);

        painter = new Painter(context);
        hexawatch = new Hexawatch(painter, pathGenerator);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight(), padding, ContextUtils.dpToPx(getContext(), 1.5f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hexawatch.drawTime(canvas, 10, 10);
    }

    public void setColors(int bgColor, int strokeColor, int fillColor) {
        painter.setColors(bgColor, strokeColor, fillColor);
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight(), padding, strokeWidth);
        invalidate();
    }

    public void setInnerHexaRatio(float innerHexaRatio) {
        pathGenerator.setInnerHexaRatio(innerHexaRatio);
        invalidate();
    }
}
