package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.common.utils.Preconditions;

import static com.nilhcem.hexawatch.common.core.WatchShape.CIRCLE;
import static com.nilhcem.hexawatch.common.utils.PathUtils.lineTo;
import static com.nilhcem.hexawatch.common.utils.PathUtils.moveTo;

public class PathGenerator {

    private static final int NB_DIGITS = 10;
    private static final int NB_HOURS = 12;
    private static final int NB_MINUTES = 6;

    private final Path bgPath = new Path();
    private final Path skeletonPath = new Path();
    private final Path[] hoursPaths = new Path[NB_HOURS];
    private final Path[] minutesPaths = new Path[NB_MINUTES];
    private final Path[] digitsPaths = new Path[NB_DIGITS];
    private final int digitsMargin;

    private WatchShape shape;
    private int width;
    private int height;
    private int padding;
    private int strokeWidth;
    private float innerHexaRatio = 0.75f;

    public PathGenerator(Context context) {
        int i;
        for (i = 0; i < NB_HOURS; i++) {
            hoursPaths[i] = new Path();
        }
        for (i = 0; i < NB_MINUTES; i++) {
            minutesPaths[i] = new Path();
        }
        for (i = 0; i < NB_DIGITS; i++) {
            digitsPaths[i] = new Path();
        }
        digitsMargin = ContextUtils.dpToPx(context, 10f);
    }

    public void setInnerHexaRatio(float innerHexaRatio) {
        this.innerHexaRatio = innerHexaRatio;
        generatePaths();
    }

    public void setShape(WatchShape shape) {
        this.shape = shape;
        generatePaths();
    }

    public Path get(PathType pathType) {
        Preconditions.checkArgument(isDataInitialized(), "Data must be initialized");

        switch (pathType) {
            case BACKGROUND:
                return bgPath;
            case SKELETON:
                return skeletonPath;
            default:
                throw new IllegalArgumentException("pathType must be BACKGROUND, or SKELETON");
        }
    }

    public Path get(PathType pathType, int index) {
        Preconditions.checkArgument(isDataInitialized(), "Data must be initialized");

        switch (pathType) {
            case HOURS:
                return hoursPaths[index];
            case MINUTES:
                return minutesPaths[index];
            case DIGITS:
                return digitsPaths[index];
            default:
                throw new IllegalArgumentException("pathType must be HOURS, MINUTES, or DIGITS");
        }
    }

    void setDimensions(int width, int height, int padding, int strokeWidth) {
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.strokeWidth = strokeWidth;
        generatePaths();
    }

    private boolean isDataInitialized() {
        return width > 0 && height > 0 && shape != null && strokeWidth != 0;
    }

    private void generatePaths() {
        if (!isDataInitialized()) {
            // Do not generate paths yet. We don't have all data so far.
            return;
        }

        float centerX = (float) width / 2f;
        float centerY = (float) height / 2f;
        float paddingRadius = ((float) Math.min(width, height) - padding) / 2f;
        float radius = paddingRadius - padding / 2 - strokeWidth / 2;

        float hexaRadius = radius * innerHexaRatio;

        PointF[] outerPoints = createOuterPoints(centerX, centerY, radius);
        PointF[] hexaPoints = createCirclePoints(centerX, centerY, hexaRadius, -120f, NB_MINUTES);

        generateBackground(centerX, centerY, paddingRadius);
        generateSkeleton(centerX, centerY, radius, outerPoints, hexaPoints);
        generateMinutes(outerPoints, hexaPoints);
        generateHours(centerX, centerY, radius, outerPoints, hexaPoints);
        generateDigits(centerX, centerY, hexaRadius - strokeWidth);
    }

    private PointF[] createOuterPoints(float centerX, float centerY, float radius) {
        if (shape == CIRCLE) {
            return createCirclePoints(centerX, centerY, radius, -90f, NB_HOURS);
        } else {
            float left = (float) padding + strokeWidth / 2;
            float top = (float) padding + strokeWidth / 2;
            float right = (float) width - padding - strokeWidth / 2;
            float bottom = (float) height - padding - strokeWidth / 2;
            float xQuarter = (right - left) / 4;

            PointF[] points = new PointF[NB_HOURS];
            points[0] = new PointF(centerX, top);
            points[1] = new PointF(right - xQuarter, top);
            points[2] = new PointF(right, top);
            points[3] = new PointF(right, centerY);
            points[4] = new PointF(right, bottom);
            points[5] = new PointF(right - xQuarter, bottom);
            points[6] = new PointF(centerX, bottom);
            points[7] = new PointF(left + xQuarter, bottom);
            points[8] = new PointF(left, bottom);
            points[9] = new PointF(left, centerY);
            points[10] = new PointF(left, top);
            points[11] = new PointF(left + xQuarter, top);
            return points;
        }
    }

    private PointF[] createCirclePoints(float centerX, float centerY, float radius, float rotation, int dividingPoints) {
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

    private void generateBackground(float centerX, float centerY, float radius) {
        Path path = bgPath;
        path.reset();

        if (shape == CIRCLE) {
            path.addCircle(centerX, centerY, radius, Path.Direction.CW);
        } else {
            path.addRect((float) padding / 2, (float) padding / 2, (float) width - padding / 2, (float) height - padding / 2, Path.Direction.CW);
        }
    }

    private void generateSkeleton(float centerX, float centerY, float radius, PointF[] outerPoints, PointF[] hexaPoints) {
        Path path = skeletonPath;
        path.reset();

        // Outer border
        if (shape == CIRCLE) {
            path.addCircle(centerX, centerY, radius, Path.Direction.CW);
        } else {
            path.addRect((float) padding + strokeWidth / 2, (float) padding + strokeWidth / 2, (float) width - padding - strokeWidth / 2, (float) height - padding - strokeWidth / 2, Path.Direction.CW);
        }

        // Minutes triangles
        for (int i = 0; i < NB_MINUTES; i++) {
            moveTo(path, hexaPoints[i]);
            lineTo(path, outerPoints[i * 2]);
            lineTo(path, hexaPoints[i == NB_MINUTES - 1 ? 0 : i + 1]);
            path.close();
        }

        // Hours separators
        for (int i = 0; i < NB_MINUTES; i++) {
            moveTo(path, hexaPoints[i]);
            lineTo(path, outerPoints[i == 0 ? NB_HOURS - 1 : i * 2 - 1]);
        }
    }

    private void generateMinutes(PointF[] outerPoints, PointF[] hexaPoints) {
        for (int i = 0; i < NB_MINUTES; i++) {
            Path path = minutesPaths[i];
            path.reset();

            moveTo(path, hexaPoints[i]);
            lineTo(path, outerPoints[i * 2]);
            lineTo(path, hexaPoints[i == NB_MINUTES - 1 ? 0 : i + 1]);
            path.close();
        }
    }

    private void generateHours(float centerX, float centerY, float radius, PointF[] outerPoints, PointF[] hexaPoints) {
        RectF oval = shape == CIRCLE ? new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius) : null;

        for (int i = 0; i < NB_HOURS; i++) {
            int innerIdx = (i % 2 + i) / 2;
            innerIdx = innerIdx == NB_MINUTES ? 0 : innerIdx;

            int nextInnerIdx = ((i + 1) % 2 + i) / 2 + 1;
            nextInnerIdx = nextInnerIdx == NB_MINUTES ? 0 : nextInnerIdx;

            Path path = hoursPaths[i];
            path.reset();

            moveTo(path, outerPoints[i]);
            if (shape == CIRCLE) {
                path.addArc(oval, i * 30 - 90, -30);
            } else {
                lineTo(path, outerPoints[i == 0 ? NB_HOURS - 1 : i - 1]);
            }
            lineTo(path, hexaPoints[innerIdx]);
            lineTo(path, outerPoints[i]);

            moveTo(path, outerPoints[i]);
            if (shape == CIRCLE) {
                path.addArc(oval, i * 30 - 90, 30);
            } else {
                lineTo(path, outerPoints[i == NB_HOURS - 1 ? 0 : i + 1]);
            }
            lineTo(path, hexaPoints[nextInnerIdx]);
            lineTo(path, outerPoints[i]);
        }
    }

    private void generateDigits(float centerX, float centerY, float radius) {
        PointF[] innerNumbersPoints = createCirclePoints(centerX, centerY, radius - digitsMargin, -120f, NB_MINUTES);
        generateDigitsFromCoords(digitsPaths[0], innerNumbersPoints, 0, 1, 2, 3, 4, 5, 0);
        generateDigitsFromCoords(digitsPaths[1], innerNumbersPoints, 1, 2, 3);
        generateDigitsFromCoords(digitsPaths[2], innerNumbersPoints, 0, 1, 2, 5, 4, 3);
        generateDigitsFromCoords(digitsPaths[3], innerNumbersPoints, 0, 1, 2, 5, 2, 3, 4);
        generateDigitsFromCoords(digitsPaths[4], innerNumbersPoints, 0, 5, 2, 1, 2, 3);
        generateDigitsFromCoords(digitsPaths[5], innerNumbersPoints, 1, 0, 5, 2, 3, 4);
        generateDigitsFromCoords(digitsPaths[6], innerNumbersPoints, 1, 0, 5, 4, 3, 2, 5);
        generateDigitsFromCoords(digitsPaths[7], innerNumbersPoints, 0, 1, 2, 3);
        generateDigitsFromCoords(digitsPaths[8], innerNumbersPoints, 2, 5, 0, 1, 2, 3, 4, 5);
        generateDigitsFromCoords(digitsPaths[9], innerNumbersPoints, 2, 5, 0, 1, 2, 3, 4);
    }

    private void generateDigitsFromCoords(Path path, PointF[] points, Integer... coords) {
        path.reset();
        moveTo(path, points[coords[0]]);
        for (int i = 1; i < coords.length; i++) {
            lineTo(path, points[coords[i]]);
        }
    }
}
