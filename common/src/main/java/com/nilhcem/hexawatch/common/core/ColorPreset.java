package com.nilhcem.hexawatch.common.core;

import android.support.annotation.StringRes;

import com.nilhcem.hexawatch.common.R;

public enum ColorPreset {

    DEFAULT(0xff202020, 0xffe0e0e0, 0xff808080, R.string.color_preset_default),
    BLUE(0xff1c5090, 0xffffffff, 0xff6e88a8, R.string.color_preset_blue),
    GREEN(0xff289465, 0xffffffff, 0xff55af88, R.string.color_preset_green),
    RED(0xffb62e2e, 0xffffffff, 0xffd06a6a, R.string.color_preset_red),
    PURPLE(0xff794787, 0xffffffff, 0xff9b79a5, R.string.color_preset_purple),
    BROWN(0xff875647, 0xffffffff, 0xffa58379, R.string.color_preset_brown),
    PINK(0xffb62e58, 0xffffffff, 0xffd06a89, R.string.color_preset_pink),
    ORANGE(0xfff2762c, 0xffffffff, 0xfff6ae82, R.string.color_preset_orange),
    YELLOW(0xfff29f1c, 0xffffffff, 0xfff6c578, R.string.color_preset_yellow),
    LIGHT_PINK(0xffcb6794, 0xffffffff, 0xffdea6bf, R.string.color_preset_light_pink),
    STEEL_BLUE(0xff1b7889, 0xffffffff, 0xff6a9da6, R.string.color_preset_steel_blue),
    DARK_RED(0xff901420, 0xffffffff, 0xffc84747, R.string.color_preset_dark_red),
    LIGHT_STEEL_BLUE(0xff6a92be, 0xffffffff, 0xffa7bed7, R.string.color_preset_light_steel_blue),
    LIGHT_GREEN(0xff6fb595, 0xffffffff, 0xffa4cebb, R.string.color_preset_light_green),
    LIGHT_PURPLE(0xff857dbd, 0xffffffff, 0xffb2add3, R.string.color_preset_light_purple),
    LIGHT_GRAY(0xffefefef, 0xff505050, 0xffacacac, R.string.color_preset_light_gray),
    NEON_BLUE(0xff21454e, 0xff73afed, 0xff246879, R.string.color_preset_neon_blue),
    NEON_PINK(0xff3d2945, 0xffefa6ec, 0xff6b5276, R.string.color_preset_neon_pink),
    NEON_GREEN(0xff253c22, 0xff73ed8a, 0xff4b6846, R.string.color_preset_neon_green),
    FRANCE(0xff22579f, 0xffffffff, 0xffc11f2a, R.string.color_preset_france),
    CHINA(0xffb62e2e, 0xffffffff, 0xfff3c831, R.string.color_preset_china),
    BLACK_AND_WHITE(0xff000000, 0xfff0f0f0, 0xffffffff, R.string.color_preset_blacknwhite);

    public final int bgColor;
    public final int strokeColor;
    public final int fillColor;
    public final int nameRes;

    ColorPreset(int bgColor, int strokeColor, int fillColor, @StringRes int nameRes) {
        this.bgColor = bgColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.nameRes = nameRes;
    }
}
