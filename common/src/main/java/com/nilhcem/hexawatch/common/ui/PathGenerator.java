package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.core.theme.Theme;
import com.nilhcem.hexawatch.common.utils.Preconditions;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.applyDimension;
import static com.nilhcem.hexawatch.common.core.WatchShape.CIRCLE;
import static com.nilhcem.hexawatch.common.utils.PathUtils.lineTo;
import static com.nilhcem.hexawatch.common.utils.PathUtils.moveTo;

class PathGenerator {

    private static final int NB_DIGITS = 10;
    private static final int NB_HOURS = 12;
    private static final int NB_MINUTES = 6;

    private final Context context;
    private final Path bgPath = new Path();
    private final Path skeletonPath = new Path();
    private final Path[] hoursPaths = new Path[NB_HOURS];
    private final Path[] minutesPaths = new Path[NB_MINUTES];
    private final Path[] digitsPaths = new Path[NB_DIGITS];

    private WatchShape shape;
    private Theme theme;
    private int width;
    private int height;
    private int padding;

    public PathGenerator(Context context) {
        this.context = context;

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
    }

    public void setShape(WatchShape shape) {
        this.shape = shape;
        generatePaths();
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        generatePaths();
    }

    public void setDimensions(int width, int height, int padding) {
        this.width = width;
        this.height = height;
        this.padding = padding;
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

    private boolean isDataInitialized() {
        return width > 0 && height > 0 && shape != null && theme != null;
    }

    private void generatePaths() {
        if (!isDataInitialized()) {
            // Do not generate paths yet. We don't have all data so far.
            return;
        }

        float strokeWidth = applyDimension(COMPLEX_UNIT_DIP, theme.strokeWidthDp, context.getResources().getDisplayMetrics());
        float centerX = (float) width / 2f;
        float centerY = (float) height / 2f;
        float paddingRadius = ((float) Math.min(width, height) - padding) / 2f;
        float radius = paddingRadius - padding / 2 - strokeWidth / 2;

        float hexaRadius = radius * theme.innerHexaRatio;

        PointF[] outerPoints = createOuterPoints(centerX, centerY, radius, strokeWidth);
        PointF[] hexaPoints = createCirclePoints(centerX, centerY, hexaRadius, -120f, NB_MINUTES);

        generateBackground(centerX, centerY, paddingRadius);
        generateSkeleton(centerX, centerY, radius, strokeWidth, outerPoints, hexaPoints);
        generateMinutes(outerPoints, hexaPoints);
        generateHours(centerX, centerY, radius, outerPoints, hexaPoints);
        generateDigits(centerX, centerY, hexaRadius - strokeWidth);
    }

    private PointF[] createOuterPoints(float centerX, float centerY, float radius, float strokeWidth) {
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

    private void generateSkeleton(float centerX, float centerY, float radius, float strokeWidth, PointF[] outerPoints, PointF[] hexaPoints) {
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
        float digitsMargin = applyDimension(COMPLEX_UNIT_DIP, 10f, context.getResources().getDisplayMetrics());
        PointF[] innerNumbersPoints = createCirclePoints(centerX, centerY, radius - digitsMargin, -120f, NB_MINUTES);
        generateDigitsFromCoords(digitsPaths[0], innerNumbersPoints, 0, 1, 2, 3, 4, 5);
        digitsPaths[0].close();

        generateDigitsFromCoords(digitsPaths[1], innerNumbersPoints, 1, 2, 3);

        generateDigitsFromCoords(digitsPaths[2], innerNumbersPoints, 0, 1, 2, 5, 4, 3);

        generateDigitsFromCoords(digitsPaths[3], innerNumbersPoints, 0, 1, 2, 3, 4);
        moveTo(digitsPaths[3], innerNumbersPoints[2]);
        lineTo(digitsPaths[3], innerNumbersPoints[5]);

        generateDigitsFromCoords(digitsPaths[4], innerNumbersPoints, 1, 2, 3);
        moveTo(digitsPaths[4], innerNumbersPoints[2]);
        lineTo(digitsPaths[4], innerNumbersPoints[5]);
        lineTo(digitsPaths[4], innerNumbersPoints[0]);

        generateDigitsFromCoords(digitsPaths[5], innerNumbersPoints, 1, 0, 5, 2, 3, 4);

        generateDigitsFromCoords(digitsPaths[6], innerNumbersPoints, 1, 0, 5, 4, 3, 2, 5);

        generateDigitsFromCoords(digitsPaths[7], innerNumbersPoints, 0, 1, 2, 3);

        generateDigitsFromCoords(digitsPaths[8], innerNumbersPoints, 0, 1, 2, 3, 4, 5);
        digitsPaths[8].close();
        moveTo(digitsPaths[8], innerNumbersPoints[2]);
        lineTo(digitsPaths[8], innerNumbersPoints[5]);

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
