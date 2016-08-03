package com.nilhcem.hexawatch.common.core;

import android.support.annotation.StringRes;

import com.nilhcem.hexawatch.common.R;

public enum Preset {

    DEFAULT(0xff202020, 0xffe0e0e0, 0xff808080, 1.5f, 0.75f, R.string.preset_default),
    CUSTOM(0xff0089b6, 0xffe8f4ff, 0xff71b8d0, 1.5f, 0.75f, R.string.preset_custom),
    BLUE(0xff1c5090, 0xffffffff, 0xff6e88a8, 1.5f, 0.75f, R.string.preset_blue),
    GREEN(0xff289465, 0xffffffff, 0xff55af88, 1.5f, 0.75f, R.string.preset_green),
    RED(0xffb62e2e, 0xffffffff, 0xffd06a6a, 1.5f, 0.75f, R.string.preset_red),
    PURPLE(0xff794787, 0xffffffff, 0xff9b79a5, 1.5f, 0.75f, R.string.preset_purple),
    BROWN(0xff875647, 0xffffffff, 0xffa58379, 1.5f, 0.75f, R.string.preset_brown),
    PINK(0xffb62e58, 0xffffffff, 0xffd06a89, 1.5f, 0.75f, R.string.preset_pink),
    ORANGE(0xfff2762c, 0xffffffff, 0xfff6ae82, 1.5f, 0.75f, R.string.preset_orange),
    YELLOW(0xfff29f1c, 0xffffffff, 0xfff6c578, 1.5f, 0.75f, R.string.preset_yellow),
    LIGHT_PINK(0xffcb6794, 0xffffffff, 0xffdea6bf, 1.5f, 0.75f, R.string.preset_light_pink),
    STEEL_BLUE(0xff1b7889, 0xffffffff, 0xff6a9da6, 1.5f, 0.75f, R.string.preset_steel_blue),
    DARK_RED(0xff901420, 0xffffffff, 0xffc84747, 1.5f, 0.75f, R.string.preset_dark_red),
    LIGHT_STEEL_BLUE(0xff6a92be, 0xffffffff, 0xffa7bed7, 1.5f, 0.75f, R.string.preset_light_steel_blue),
    LIGHT_GREEN(0xff6fb595, 0xffffffff, 0xffa4cebb, 1.5f, 0.75f, R.string.preset_light_green),
    LIGHT_PURPLE(0xff857dbd, 0xffffffff, 0xffb2add3, 1.5f, 0.75f, R.string.preset_light_purple),
    LIGHT_GRAY(0xffefefef, 0xff505050, 0xffacacac, 1.5f, 0.75f, R.string.preset_light_gray),
    NEON_BLUE(0xff21454e, 0xff73afed, 0xff246879, 1.5f, 0.75f, R.string.preset_neon_blue),
    NEON_PINK(0xff3d2945, 0xffefa6ec, 0xff6b5276, 1.5f, 0.75f, R.string.preset_neon_pink),
    NEON_GREEN(0xff253c22, 0xff73ed8a, 0xff4b6846, 1.5f, 0.75f, R.string.preset_neon_green),
    FRANCE(0xff22579f, 0xffffffff, 0xffc11f2a, 1.5f, 0.75f, R.string.preset_france),
    CHINA(0xffb62e2e, 0xffffffff, 0xfff3c831, 1.5f, 0.75f, R.string.preset_china),
    BLACK_AND_WHITE(0xff000000, 0xfff0f0f0, 0xffffffff, 1.5f, 0.75f, R.string.preset_blacknwhite),
    NIGHT_STAR(0xff340067, 0xffc5d1f1, 0xff6a72a7, 1.7f, 0.4f, R.string.preset_night_star),
    PSYCHEDELIC(0xff7339d3, 0xffffad00, 0xffc113da, 1.f, 0.18f, R.string.preset_psychedelic),
    BLUE_TRIANGLES(0xff1078b6, 0xffbaddff, 0xff71a0d0, 5.f, 0.58f, R.string.preset_blue_triangles);

    public final int bgColor;
    public final int strokeColor;
    public final int fillColor;
    public final float strokeSizeDp;
    public final float innerHexaRatio;
    public final int nameRes;

    Preset(int bgColor, int strokeColor, int fillColor, float strokeSizeDp, float innerHexaRatio, @StringRes int nameRes) {
        this.bgColor = bgColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeSizeDp = strokeSizeDp;
        this.innerHexaRatio = innerHexaRatio;
        this.nameRes = nameRes;
    }

    public static Preset fromName(String name) {
        Preset preset = null;

        if (name != null) {
            try {
                preset = Preset.valueOf(name);
            } catch (IllegalArgumentException e) {
                // Do nothing
            }
        }

        if (preset == null) {
            preset = Preset.DEFAULT;
        }
        return preset;
    }
}
