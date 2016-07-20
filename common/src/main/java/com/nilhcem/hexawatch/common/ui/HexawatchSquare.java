package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import com.nilhcem.hexawatch.common.ui.painter.Painter;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

import static com.nilhcem.hexawatch.common.utils.PathUtils.lineTo;
import static com.nilhcem.hexawatch.common.utils.PathUtils.moveTo;

public class HexawatchSquare implements Hexawatch {

    private final Painter painter;

    private final Path bgPath;
    private final Path skeletonPath;
    private final Path[] hoursPaths;
    private final Path[] minutesPaths;
    private final Path[] digitsPaths;

    HexawatchSquare(Context context, int width, int height, int strokeWidth, int paddingWidth, float innerHexaRatio, Painter painter) {
        this.painter = painter;
        this.painter.setWidths(strokeWidth, paddingWidth);

        float centerX = (float) width / 2f;
        float centerY = (float) height / 2f;
        float paddingRadius = ((float) Math.min(width, height) - paddingWidth) / 2f;
        float radius = paddingRadius - paddingWidth / 2 - strokeWidth / 2;

        float hexaRadius = radius * innerHexaRatio;

        RectF rect = new RectF(paddingWidth + strokeWidth / 2, paddingWidth + strokeWidth / 2, (float) width - paddingWidth - strokeWidth / 2, (float) height - paddingWidth - strokeWidth / 2);

        PointF[] outerPoints = getOuterPoints(centerX, centerY, rect);
        PointF[] hexaPoints = getCirclePoints(centerX, centerY, hexaRadius, -120f, 6);

        bgPath = createBackgroundPath(new RectF(paddingWidth / 2, paddingWidth / 2, width - paddingWidth / 2, height - paddingWidth / 2));
        skeletonPath = createSkeletonPath(rect, outerPoints, hexaPoints);
        minutesPaths = createMinutesPaths(outerPoints, hexaPoints);
        hoursPaths = createHoursPaths(outerPoints, hexaPoints);
        digitsPaths = createDigitsPaths(context, centerX, centerY, hexaRadius - strokeWidth);
    }

    @Override
    public void drawTime(Canvas canvas, int hours, int minutes) {
        painter.draw(canvas, bgPath, skeletonPath, hoursPaths[hours], minutesPaths[minutes / 10], digitsPaths[minutes % 10]);
    }

    private Path createBackgroundPath(RectF rect) {
        Path path = new Path();
        path.addRect(rect, Path.Direction.CW);
        return path;
    }

    private Path createSkeletonPath(RectF rect, PointF[] outerPoints, PointF[] hexaPoints) {
        Path path = new Path();

        // Outer border
        path.addRect(rect, Path.Direction.CW);

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

    private Path[] createHoursPaths(PointF[] outerPoints, PointF[] hexaPoints) {
        Path[] paths = new Path[12];

        for (int i = 0; i < 12; i++) {
            int innerIdx = (i % 2 + i) / 2;
            innerIdx = innerIdx == 6 ? 0 : innerIdx;

            int nextInnerIdx = ((i + 1) % 2 + i) / 2 + 1;
            nextInnerIdx = nextInnerIdx == 6 ? 0 : nextInnerIdx;

            paths[i] = new Path();
            moveTo(paths[i], outerPoints[i]);
            lineTo(paths[i], outerPoints[i == 0 ? 11 : i - 1]);
            lineTo(paths[i], hexaPoints[innerIdx]);
            lineTo(paths[i], outerPoints[i]);

            moveTo(paths[i], outerPoints[i]);
            lineTo(paths[i], outerPoints[i == 11 ? 0 : i + 1]);
            lineTo(paths[i], hexaPoints[nextInnerIdx]);
            lineTo(paths[i], outerPoints[i]);
        }
        return paths;
    }

    private Path[] createDigitsPaths(Context context, float centerX, float centerY, float radius) {
        Path[] paths = new Path[10];

        PointF[] innerNumbersPoints = getCirclePoints(centerX, centerY, radius - ContextUtils.dpToPx(context, 10f), -120f, 6);
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

    private PointF[] getOuterPoints(float centerX, float centerY, RectF rect) {
        float xQuarter = (rect.right - rect.left) / 4;

        PointF[] points = new PointF[12];
        points[0] = new PointF(centerX, rect.top);
        points[1] = new PointF(rect.right - xQuarter, rect.top);
        points[2] = new PointF(rect.right, rect.top);
        points[3] = new PointF(rect.right, centerY);
        points[4] = new PointF(rect.right, rect.bottom);
        points[5] = new PointF(rect.right - xQuarter, rect.bottom);
        points[6] = new PointF(centerX, rect.bottom);
        points[7] = new PointF(rect.left + xQuarter, rect.bottom);
        points[8] = new PointF(rect.left, rect.bottom);
        points[9] = new PointF(rect.left, centerY);
        points[10] = new PointF(rect.left, rect.top);
        points[11] = new PointF(rect.left + xQuarter, rect.top);

        return points;
    }
}
