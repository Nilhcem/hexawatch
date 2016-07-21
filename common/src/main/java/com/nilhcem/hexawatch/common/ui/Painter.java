package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.nilhcem.hexawatch.common.core.ColorPreset;
import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

public class Painter {

    private final Paint bgPaint;
    private final Paint strokePaint;
    private final Paint fillPaint;
    private final Paint paddingPaint;

    private WatchMode mode;

    private int bgColor;
    private int strokeColor;
    private int fillColor;

    private int strokeWidth;
    private int paddingWidth;
    private int strokeWidthAmbient;

    public Painter(Context context) {
        strokeWidthAmbient = ContextUtils.dpToPx(context, 1f);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);

        paddingPaint = new Paint();
        paddingPaint.setColor(Color.BLACK);
        paddingPaint.setStyle(Paint.Style.STROKE);
    }

    public Painter(Context context, ColorPreset colorPreset) {
        this(context);
        setColor(colorPreset);
    }

    public Painter(Context context, int bgColor, int strokeColor, int fillColor) {
        this(context);
        setColor(bgColor, strokeColor, fillColor);
    }

    public void draw(Canvas canvas, Path background, Path skeleton, Path hours, Path minutes, Path digits) {
        canvas.drawPath(background, bgPaint);

        if (mode == WatchMode.AMBIENT) {
            canvas.drawPath(skeleton, strokePaint);
        }
        canvas.drawPath(hours, fillPaint);
        canvas.drawPath(minutes, fillPaint);
        canvas.drawPath(digits, mode == WatchMode.INTERACTIVE ? strokePaint : fillPaint);
        if (mode == WatchMode.INTERACTIVE) {
            canvas.drawPath(skeleton, strokePaint);
        }

        if (mode != WatchMode.LOW_BIT) {
            canvas.drawPath(background, paddingPaint);
        }
    }

    public void setColor(ColorPreset colorPreset) {
        setColor(colorPreset.bgColor, colorPreset.strokeColor, colorPreset.fillColor);
    }

    public void setColor(int bgColor, int strokeColor, int fillColor) {
        this.bgColor = bgColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        setPaintColors(bgColor, strokeColor, fillColor);
    }

    void setMode(WatchMode mode) {
        this.mode = mode;

        switch (mode) {
            case INTERACTIVE:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.FILL);
                setPaintColors(bgColor, strokeColor, fillColor);
                setPaintWidths(strokeWidth, paddingWidth);
                break;
            case AMBIENT:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.STROKE);
                setPaintColors(Color.BLACK, 0xff505050, 0xffdddddd);
                setPaintWidths(strokeWidthAmbient, paddingWidth);
                break;
            case LOW_BIT:
                setAntiAlias(false);
                setFillPaintStyle(Paint.Style.STROKE);
                setPaintColors(Color.BLACK, Color.WHITE, Color.WHITE);
                setPaintWidths(strokeWidthAmbient, paddingWidth);
                break;
            default:
                throw new IllegalArgumentException("This should not happen");
        }
    }

    void setWidths(int strokeWidth, int paddingWidth) {
        this.strokeWidth = strokeWidth;
        this.paddingWidth = paddingWidth;
        setPaintWidths(strokeWidth, paddingWidth);
    }

    private void setPaintColors(int bgColor, int strokeColor, int fillColor) {
        bgPaint.setColor(bgColor);
        strokePaint.setColor(strokeColor);
        fillPaint.setColor(fillColor);
    }

    private void setPaintWidths(int strokeWidth, int paddingWidth) {
        bgPaint.setStrokeWidth(strokeWidth);
        strokePaint.setStrokeWidth(strokeWidth);
        fillPaint.setStrokeWidth(strokeWidth);
        paddingPaint.setStrokeWidth(paddingWidth);
    }

    private void setAntiAlias(boolean antiAlias) {
        bgPaint.setAntiAlias(antiAlias);
        strokePaint.setAntiAlias(antiAlias);
        fillPaint.setAntiAlias(antiAlias);
        paddingPaint.setAntiAlias(antiAlias);
    }

    private void setFillPaintStyle(Paint.Style style) {
        fillPaint.setStyle(style);
    }
}
