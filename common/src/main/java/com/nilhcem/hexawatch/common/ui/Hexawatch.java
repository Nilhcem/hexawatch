package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;

import com.nilhcem.hexawatch.common.core.WatchMode;
import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.core.theme.Theme;

import static com.nilhcem.hexawatch.common.ui.PathType.BACKGROUND;
import static com.nilhcem.hexawatch.common.ui.PathType.DIGITS;
import static com.nilhcem.hexawatch.common.ui.PathType.HOURS;
import static com.nilhcem.hexawatch.common.ui.PathType.MINUTES;
import static com.nilhcem.hexawatch.common.ui.PathType.SKELETON;

public class Hexawatch {

    private final Painter painter;
    private final PathGenerator pathGenerator;

    public Hexawatch(Context context) {
        painter = new Painter(context);
        pathGenerator = new PathGenerator(context);
        setMode(WatchMode.INTERACTIVE);
    }

    public void setShape(WatchShape shape) {
        pathGenerator.setShape(shape);
    }

    public void setMode(WatchMode mode) {
        painter.setMode(mode);
    }

    public void setDimensions(int width, int height, int padding) {
        painter.setPadding(padding);
        pathGenerator.setDimensions(width, height, padding);
    }

    public void setTheme(Theme theme) {
        painter.setTheme(theme);
        pathGenerator.setTheme(theme);
    }

    public void drawTime(Canvas canvas, int hours, int minutes) {
        painter.draw(canvas,
                pathGenerator.get(BACKGROUND),
                pathGenerator.get(SKELETON),
                pathGenerator.get(HOURS, hours),
                pathGenerator.get(MINUTES, minutes / 10),
                pathGenerator.get(DIGITS, minutes % 10));
    }
}
