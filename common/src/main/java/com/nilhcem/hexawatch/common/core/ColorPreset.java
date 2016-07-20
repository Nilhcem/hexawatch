package com.nilhcem.hexawatch.common.core;

public enum ColorPreset {

    BLACK(0xff333333, 0xffe6e6e6, 0xffb9b9b9),
    PINK(0xff952261, 0xfff1e5eb, 0xffcd79a6),
    BLUE(0xff387b94, 0xffebddd4, 0xff1f6179);

    public final int bgColor;
    public final int strokeColor;
    public final int fillColor;

    ColorPreset(int bgColor, int strokeColor, int fillColor) {
        this.bgColor = bgColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }
}
