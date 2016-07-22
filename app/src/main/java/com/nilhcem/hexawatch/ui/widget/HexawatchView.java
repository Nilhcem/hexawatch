package com.nilhcem.hexawatch.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.Hexawatch;
import com.nilhcem.hexawatch.common.ui.Painter;
import com.nilhcem.hexawatch.common.ui.PathGenerator;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

public class HexawatchView extends View {

    private final Hexawatch hexawatch;
    private final Painter painter;

    public HexawatchView(Context context) {
        this(context, null);
    }

    public HexawatchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        PathGenerator pathGenerator = new PathGenerator(context);
        pathGenerator.setShape(WatchShape.CIRCLE);

        painter = new Painter(context);
        hexawatch = new Hexawatch(painter, pathGenerator);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        hexawatch.setDimensions(getMeasuredWidth(), getMeasuredHeight(), ContextUtils.dpToPx(getContext(), 1f), ContextUtils.dpToPx(getContext(), 1.5f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hexawatch.drawTime(canvas, 10, 10);
    }

    public void setColor(ColorPreset colorPreset) {
        painter.setColor(colorPreset);
        invalidate();
    }
}
