package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

class Painter {

    private final Context context;
    private final Paint bgPaint;
    private final Paint strokePaint;
    private final Paint fillPaint;
    private final Paint paddingPaint;

    private WatchMode mode;
    private WatchTheme theme;

    Painter(Context context) {
        this.context = context;

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

    void draw(Canvas canvas, Path background, Path skeleton, Path hours, Path minutes, Path digits) {
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

    void setMode(WatchMode mode) {
        this.mode = mode;

        switch (mode) {
            case INTERACTIVE:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.FILL);
                setTheme(theme);
                break;
            case AMBIENT:
                setAntiAlias(true);
                setFillPaintStyle(Paint.Style.STROKE);
                setPaintColors(Color.BLACK, 0xff505050, 0xffdddddd);
                setPaintWidths(1f);
                break;
            case LOW_BIT:
                setAntiAlias(false);
                setFillPaintStyle(Paint.Style.STROKE);
                setPaintColors(Color.BLACK, Color.WHITE, Color.WHITE);
                setPaintWidths(1f);
                break;
            default:
                throw new IllegalArgumentException("This should not happen");
        }
    }

    void setPadding(int padding) {
        paddingPaint.setStrokeWidth(padding);
    }

    void setTheme(WatchTheme theme) {
        this.theme = theme;
        if (theme != null) {
            setPaintColors(theme.bgColor, theme.strokeColor, theme.fillColor);
            setPaintWidths(theme.strokeWidthDp);
        }
    }

    private void setPaintColors(int bgColor, int strokeColor, int fillColor) {
        bgPaint.setColor(bgColor);
        strokePaint.setColor(strokeColor);
        fillPaint.setColor(fillColor);
    }

    private void setPaintWidths(float strokeWidthDp) {
        int strokeWidth = ContextUtils.dpToPx(context, strokeWidthDp);
        bgPaint.setStrokeWidth(strokeWidth);
        strokePaint.setStrokeWidth(strokeWidth);
        fillPaint.setStrokeWidth(strokeWidth);
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
