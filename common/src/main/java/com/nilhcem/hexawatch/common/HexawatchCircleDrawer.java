package com.nilhcem.hexawatch.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import com.nilhcem.hexawatch.common.utils.ContextUtils;

import java.util.Calendar;

import static com.nilhcem.hexawatch.common.utils.PathUtils.lineTo;
import static com.nilhcem.hexawatch.common.utils.PathUtils.moveTo;

public class HexawatchCircleDrawer {

    private Context context;
    private float strokeWidth;

    private Calendar calendar = Calendar.getInstance();

    private Path bgPath;
    private Path skeletonPath;
    private Path[] hoursPaths;
    private Path[] minutesPaths;
    private Path[] digitsPaths;

    private Paint bgPaint;
    private Paint strokePaint;
    private Paint fillPaint;

    // TODO: builder instead (new Hexawatch.Builder().circle().strokeWidth(2f).fillColor().strokeColor().build())
    // TODO: Possibilité également de changer en temps réel la couleur ? le mode ambiant ?
    public HexawatchCircleDrawer(Context context, int w, int h, float strokeWidth, int bgColor, int strokeColor, int fillColor) {
        this.context = context;
        this.strokeWidth = ContextUtils.dpToPx(context, strokeWidth);

        float centerX = (float) w / 2f;
        float centerY = (float) h / 2f;
        float radius = ((float) Math.min(w, h) - this.strokeWidth) / 2f;

        float outerRadius = radius - this.strokeWidth / 2f;
        float hexaRadius = outerRadius * 0.75f;

        PointF[] outerPoints = getCirclePoints(centerX, centerY, outerRadius, -90f, 12);
        PointF[] hexaPoints = getCirclePoints(centerX, centerY, hexaRadius, -120f, 6);

        bgPath = createBackgroundPath(centerX, centerY, radius);
        skeletonPath = createSkeletonPath(centerX, centerY, radius, outerPoints, hexaPoints);
        minutesPaths = createMinutesPaths(outerPoints, hexaPoints);
        hoursPaths = createHoursPaths(centerX, centerY, outerRadius, outerPoints, hexaPoints);
        digitsPaths = createDigitsPaths(centerX, centerY, hexaRadius - this.strokeWidth);

        bgPaint = new Paint();
        bgPaint.setStrokeWidth(this.strokeWidth);
        bgPaint.setStyle(Style.FILL_AND_STROKE);
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);

        strokePaint = new Paint();
        strokePaint.setStrokeWidth(this.strokeWidth);
        strokePaint.setStyle(Style.STROKE);
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(strokeColor);

        fillPaint = new Paint();
        fillPaint.setStrokeWidth(this.strokeWidth);
        fillPaint.setStyle(Style.FILL);
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(fillColor);
    }

    public void draw(Canvas canvas, Rect bounds) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        drawTime(canvas, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
    }

    private void drawTime(Canvas canvas, int hours, int minutes) {
        canvas.drawPath(bgPath, bgPaint);
        canvas.drawPath(hoursPaths[hours], fillPaint);
        canvas.drawPath(minutesPaths[minutes / 10], fillPaint);
        canvas.drawPath(digitsPaths[minutes % 10], strokePaint);
        canvas.drawPath(skeletonPath, strokePaint);
    }

    private Path createBackgroundPath(float centerX, float centerY, float radius) {
        Path path = new Path();
        path.addCircle(centerX, centerY, radius, Path.Direction.CW);
        return path;
    }

    private Path createSkeletonPath(float centerX, float centerY, float radius, PointF[] outerPoints, PointF[] hexaPoints) {
        Path path = new Path();

        // Outer circle
        path.addCircle(centerX, centerY, radius, Path.Direction.CW);

        // Minutes triangles
        for (int i = 0; i < 6; i++) {
            moveTo(path, hexaPoints[i]);
            lineTo(path, outerPoints[i * 2]);
            lineTo(path, hexaPoints[i == 5 ? 0 : i + 1]);
            path.close();
        }

        // Hours separators
        for (int i = 0; i < 6; i++) {
            moveTo(path, hexaPoints[i]);
            lineTo(path, outerPoints[i == 0 ? 11 : i * 2 - 1]);
        }

        return path;
    }

    private Path[] createMinutesPaths(PointF[] outerPoints, PointF[] hexaPoints) {
        Path[] paths = new Path[6];

        for (int i = 0; i < 6; i++) {
            paths[i] = new Path();
            moveTo(paths[i], hexaPoints[i]);
            lineTo(paths[i], outerPoints[i * 2]);
            lineTo(paths[i], hexaPoints[i == 5 ? 0 : i + 1]);
            paths[i].close();
        }
        return paths;
    }

    private Path[] createHoursPaths(float centerX, float centerY, float radius, PointF[] outerPoints, PointF[] hexaPoints) {
        Path[] paths = new Path[12];
        RectF oval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        for (int i = 0; i < 12; i++) {
            int innerIdx = (i % 2 + i) / 2;
            innerIdx = innerIdx == 6 ? 0 : innerIdx;

            int nextInnerIdx = ((i + 1) % 2 + i) / 2 + 1;
            nextInnerIdx = nextInnerIdx == 6 ? 0 : nextInnerIdx;

            paths[i] = new Path();
            moveTo(paths[i], outerPoints[i]);
            paths[i].addArc(oval, i * 30 - 90, -30);
            lineTo(paths[i], hexaPoints[innerIdx]);
            lineTo(paths[i], outerPoints[i]);

            moveTo(paths[i], outerPoints[i]);
            paths[i].addArc(oval, i * 30 - 90, 30);
            lineTo(paths[i], hexaPoints[nextInnerIdx]);
            lineTo(paths[i], outerPoints[i]);
        }
        return paths;
    }

    private Path[] createDigitsPaths(float centerX, float centerY, float radius) {
        Path[] paths = new Path[10];

        PointF[] innerNumbersPoints = getCirclePoints(centerX, centerY, radius - ContextUtils.dpToPx(context, 6f), -120f, 6);
        paths[0] = createPathFromCoords(innerNumbersPoints, 0, 1, 2, 3, 4, 5, 0);
        paths[1] = createPathFromCoords(innerNumbersPoints, 1, 2, 3);
        paths[2] = createPathFromCoords(innerNumbersPoints, 0, 1, 2, 5, 4, 3);
        paths[3] = createPathFromCoords(innerNumbersPoints, 0, 1, 2, 5, 2, 3, 4);
        paths[4] = createPathFromCoords(innerNumbersPoints, 0, 5, 2, 1, 2, 3);
        paths[5] = createPathFromCoords(innerNumbersPoints, 1, 0, 5, 2, 3, 4);
        paths[6] = createPathFromCoords(innerNumbersPoints, 1, 0, 5, 4, 3, 2, 5);
        paths[7] = createPathFromCoords(innerNumbersPoints, 0, 1, 2, 3);
        paths[8] = createPathFromCoords(innerNumbersPoints, 2, 5, 0, 1, 2, 3, 4, 5);
        paths[9] = createPathFromCoords(innerNumbersPoints, 2, 5, 0, 1, 2, 3, 4);

        return paths;
    }

    private Path createPathFromCoords(PointF[] points, Integer... coords) {
        Path path = new Path();
        moveTo(path, points[coords[0]]);
        for (int i = 1; i < coords.length; i++) {
            lineTo(path, points[coords[i]]);
        }
        return path;
    }

    private PointF[] getCirclePoints(float centerX, float centerY, float radius, float rotation, int dividingPoints) {
        PointF[] points = new PointF[dividingPoints];

        float x;
        float y;
        float angle;
        for (int i = 0; i < dividingPoints; i++) {
            angle = i * (360 / dividingPoints) + rotation;
            x = (float) (centerX + radius * Math.cos(Math.toRadians(angle)));
            y = (float) (centerY + radius * Math.sin(Math.toRadians(angle)));
            points[i] = new PointF(x, y);
        }
        return points;
    }
}
