package com.nilhcem.hexawatch.common.ui;

import android.graphics.Canvas;

import com.nilhcem.hexawatch.common.core.WatchMode;

import static com.nilhcem.hexawatch.common.ui.PathType.BACKGROUND;
import static com.nilhcem.hexawatch.common.ui.PathType.DIGITS;
import static com.nilhcem.hexawatch.common.ui.PathType.HOURS;
import static com.nilhcem.hexawatch.common.ui.PathType.MINUTES;
import static com.nilhcem.hexawatch.common.ui.PathType.SKELETON;

public class Hexawatch {

    private final Painter painter;
    private final PathGenerator pathGenerator;

    public Hexawatch(Painter painter, PathGenerator pathGenerator) {
        this.painter = painter;
        this.pathGenerator = pathGenerator;
        setMode(WatchMode.INTERACTIVE);
    }

    public void setMode(WatchMode mode) {
        painter.setMode(mode);
    }

    public void setWidths(int strokeWidth, int paddingWidth) {
        painter.setWidths(strokeWidth, paddingWidth);
        pathGenerator.setWidths(strokeWidth, paddingWidth);
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
