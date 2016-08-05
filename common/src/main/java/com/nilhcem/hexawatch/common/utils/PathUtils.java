package com.nilhcem.hexawatch.common.utils;

import android.graphics.Path;
import android.graphics.PointF;

public class PathUtils {

    private PathUtils() {
        throw new UnsupportedOperationException();
    }

    public static void moveTo(Path path, PointF point) {
        path.moveTo(point.x, point.y);
    }

    public static void lineTo(Path path, PointF point) {
        path.lineTo(point.x, point.y);
    }
}
