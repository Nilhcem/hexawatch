package com.nilhcem.hexawatch.common.core;

public enum ColorPreset {

    DEFAULT(0xff202020, 0xffe0e0e0, 0xff808080),
    BLUE(0xff1c5090, 0xffffffff, 0xff6e88a8),
    GREEN(0xff289465, 0xffffffff, 0xff55af88),
    RED(0xffb62e2e, 0xffffffff, 0xffd06a6a),
    PURPLE(0xff794787, 0xffffffff, 0xff9b79a5),
    BROWN(0xff875647, 0xffffffff, 0xffa58379),
    PINK(0xffb62e58, 0xffffffff, 0xffd06a89),
    ORANGE(0xfff2762c, 0xffffffff, 0xfff6ae82),
    YELLOW(0xfff29f1c, 0xffffffff, 0xfff6c578),
    LIGHT_PINK(0xffcb6794, 0xffffffff, 0xffdea6bf),
    STEEL_BLUE(0xff1b7889, 0xffffffff, 0xff6a9da6),
    DARK_RED(0xff901420, 0xffffffff, 0xffc84747),
    LIGHT_STEEL_BLUE(0xff6a92be, 0xffffffff, 0xffa7bed7),
    LIGHT_GREEN(0xff6fb595, 0xffffffff, 0xffa4cebb),
    LIGHT_PURPLE(0xff857dbd, 0xffffffff, 0xffb2add3),
    LIGHT_GRAY(0xffefefef, 0xff505050, 0xffacacac),
    NEON_BLUE(0xff21454e, 0xff73afed, 0xff246879),
    NEON_PINK(0xff3d2945, 0xffefa6ec, 0xff6b5276),
    NEON_GREEN(0xff253c22, 0xff73ed8a, 0xff4b6846),
    FRANCE(0xff22579f, 0xffffffff, 0xffc11f2a),
    CHINA(0xffb62e2e, 0xffffffff, 0xfff3c831),
    BLACK_AND_WHITE(0xff000000, 0xfff0f0f0, 0xffffffff);


    public final int bgColor;
    public final int strokeColor;
    public final int fillColor;

    ColorPreset(int bgColor, int strokeColor, int fillColor) {
        this.bgColor = bgColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }
}
