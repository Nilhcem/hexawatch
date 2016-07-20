package com.nilhcem.hexawatch.common.ui.painter;

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
    private final Paint marginPaint;

    private WatchMode mode;

    private int bgColor;
    private int strokeColor;
    private int fillColor;

    private int strokeWidth;
    private int marginWidth;
    private int strokeWidthAmbient;

    public Painter(Context context) {
        strokeWidthAmbient = ContextUtils.dpToIntPx(context, 1f);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);

        marginPaint = new Paint();
        marginPaint.setColor(Color.BLACK);
        marginPaint.setStyle(Paint.Style.STROKE);

        setMode(WatchMode.INTERACTIVE);
    }

    public void setMode(WatchMode mode) {
        this.mode = mode;

        switch (mode) {
            case INTERACTIVE:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.FILL);
                setColor(bgColor, strokeColor, fillColor);
                setWidths(strokeWidth, marginWidth);
                break;
            case AMBIENT:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.STROKE);
                setColor(Color.BLACK, 0xff505050, 0xffdddddd);
                setWidths(strokeWidthAmbient, marginWidth);
                break;
            case LOW_BIT:
                setAntiAlias(false);
                setFillPaintStyle(Paint.Style.STROKE);
                setColor(Color.BLACK, Color.WHITE, Color.WHITE);
                setWidths(strokeWidthAmbient, marginWidth);
                break;
            default:
                throw new IllegalArgumentException("This should not happen");
        }
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
            canvas.drawPath(background, marginPaint);
        }
    }

    public void setColor(ColorPreset colorPreset) {
        setColor(colorPreset.bgColor, colorPreset.strokeColor, colorPreset.fillColor);
    }

    public void setColor(int bgColor, int strokeColor, int fillColor) {
        bgPaint.setColor(bgColor);
        strokePaint.setColor(strokeColor);
        fillPaint.setColor(fillColor);

        if (mode == WatchMode.INTERACTIVE) {
            this.bgColor = bgColor;
            this.strokeColor = strokeColor;
            this.fillColor = fillColor;
        }
    }

    public void setWidths(int strokeWidth, int marginWidth) {
        bgPaint.setStrokeWidth(strokeWidth);
        strokePaint.setStrokeWidth(strokeWidth);
        fillPaint.setStrokeWidth(strokeWidth);
        marginPaint.setStrokeWidth(marginWidth);

        if (mode == WatchMode.INTERACTIVE) {
            this.strokeWidth = strokeWidth;
            this.marginWidth = marginWidth;
        }
    }

    private void setAntiAlias(boolean antiAlias) {
        bgPaint.setAntiAlias(antiAlias);
        strokePaint.setAntiAlias(antiAlias);
        fillPaint.setAntiAlias(antiAlias);
        marginPaint.setAntiAlias(antiAlias);
    }

    private void setFillPaintStyle(Paint.Style style) {
        fillPaint.setStyle(style);
    }
}
