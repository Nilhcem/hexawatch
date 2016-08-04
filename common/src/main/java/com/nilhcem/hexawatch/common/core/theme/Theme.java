package com.nilhcem.hexawatch.common.core.theme;

public class Theme {

    public final int bgColor;
    public final int fillColor;
    public final int strokeColor;
    public final float strokeWidthDp;
    public final float innerHexaRatio;

    public Theme(int bgColor, int fillColor, int strokeColor, float strokeWidthDp, float innerHexaRatio) {
        this.bgColor = bgColor;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidthDp = strokeWidthDp;
        this.innerHexaRatio = innerHexaRatio;
    }
}
