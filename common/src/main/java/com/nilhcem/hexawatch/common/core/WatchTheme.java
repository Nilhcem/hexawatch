package com.nilhcem.hexawatch.common.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.util.Log;

import com.nilhcem.hexawatch.common.R;

import java.util.Locale;

public class WatchTheme implements Parcelable {

    public final int bgColor;
    public final int fillColor;
    public final int strokeColor;
    public final float strokeWidthDp;
    public final float innerHexaRatio;

    public static final Parcelable.Creator<WatchTheme> CREATOR = new Parcelable.Creator<WatchTheme>() {
        @Override
        public WatchTheme createFromParcel(Parcel source) {
            return new WatchTheme(source);
        }

        @Override
        public WatchTheme[] newArray(int size) {
            return new WatchTheme[size];
        }
    };

    public WatchTheme(int bgColor, int fillColor, int strokeColor, float strokeWidthDp, float innerHexaRatio) {
        this.bgColor = bgColor;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidthDp = strokeWidthDp;
        this.innerHexaRatio = innerHexaRatio;
    }

    protected WatchTheme(Parcel in) {
        bgColor = in.readInt();
        fillColor = in.readInt();
        strokeColor = in.readInt();
        strokeWidthDp = in.readFloat();
        innerHexaRatio = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bgColor);
        dest.writeInt(fillColor);
        dest.writeInt(strokeColor);
        dest.writeFloat(strokeWidthDp);
        dest.writeFloat(innerHexaRatio);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "WatchTheme{bgColor=#%X, fillColor=#%X, strokeColor=#%X, strokeWidthDp=%.2f, innerHexaRatio=%.2f}",
                bgColor, fillColor, strokeColor, strokeWidthDp, innerHexaRatio);
    }

    public enum Preset {
        DEFAULT(R.string.theme_preset_default, new WatchTheme(0xff181818, 0xff3a3a3a, 0xffa2a2a2, 1.5f, 0.75f)),
        CUSTOM(R.string.theme_preset_custom, new WatchTheme(0xff006f94, 0xff5b95a8, 0xffd1ebff, 1.5f, 0.75f)),
        BLUE(R.string.theme_preset_blue, new WatchTheme(0xff1c5090, 0xff6e88a8, 0xffffffff, 1.5f, 0.75f)),
        GREEN(R.string.theme_preset_green, new WatchTheme(0xff00b387, 0xff498778, 0xffe8fff4, 1.5f, 0.75f)),
        RED(R.string.theme_preset_red, new WatchTheme(0xffb62e2e, 0xffd06a6a, 0xffffffff, 1.5f, 0.75f)),
        PURPLE(R.string.theme_preset_purple, new WatchTheme(0xff794787, 0xff9b79a5, 0xffffffff, 1.5f, 0.75f)),
        BROWN(R.string.theme_preset_brown, new WatchTheme(0xff875647, 0xffa58379, 0xffffffff, 1.5f, 0.75f)),
        PINK(R.string.theme_preset_pink, new WatchTheme(0xffb62e58, 0xffd06a89, 0xffffffff, 1.5f, 0.75f)),
        ORANGE(R.string.theme_preset_orange, new WatchTheme(0xfff2762c, 0xfff6ae82, 0xffffffff, 1.5f, 0.75f)),
        YELLOW(R.string.theme_preset_yellow, new WatchTheme(0xfff29f1c, 0xfff6c578, 0xffffffff, 1.5f, 0.75f)),
        LIGHT_PINK(R.string.theme_preset_light_pink, new WatchTheme(0xffcb6794, 0xffdea6bf, 0xffffffff, 1.5f, 0.75f)),
        STEEL_BLUE(R.string.theme_preset_steel_blue, new WatchTheme(0xff1b7889, 0xff6a9da6, 0xffffffff, 1.5f, 0.75f)),
        DARK_RED(R.string.theme_preset_dark_red, new WatchTheme(0xff901420, 0xffc84747, 0xffffffff, 1.5f, 0.75f)),
        LIGHT_STEEL_BLUE(R.string.theme_preset_light_steel_blue, new WatchTheme(0xff6a92be, 0xffa7bed7, 0xffffffff, 1.5f, 0.75f)),
        LIGHT_GREEN(R.string.theme_preset_light_green, new WatchTheme(0xff6fb595, 0xffa4cebb, 0xffffffff, 1.5f, 0.75f)),
        LIGHT_PURPLE(R.string.theme_preset_light_purple, new WatchTheme(0xff857dbd, 0xffb2add3, 0xffffffff, 1.5f, 0.75f)),
        LIGHT_GRAY(R.string.theme_preset_light_gray, new WatchTheme(0xffefefef, 0xffacacac, 0xff505050, 1.5f, 0.75f)),
        NEON_BLUE(R.string.theme_preset_neon_blue, new WatchTheme(0xff21454e, 0xff246879, 0xff73afed, 1.5f, 0.75f)),
        NEON_PINK(R.string.theme_preset_neon_pink, new WatchTheme(0xff3d2945, 0xff6b5276, 0xffefa6ec, 1.5f, 0.75f)),
        NEON_GREEN(R.string.theme_preset_neon_green, new WatchTheme(0xff253c22, 0xff4b6846, 0xff73ed8a, 1.5f, 0.75f)),
        FRANCE(R.string.theme_preset_france, new WatchTheme(0xff22579f, 0xffc11f2a, 0xffffffff, 1.5f, 0.75f)),
        CHINA(R.string.theme_preset_china, new WatchTheme(0xffb62e2e, 0xfff3c831, 0xffffffff, 1.5f, 0.75f)),
        BLACK_AND_WHITE(R.string.theme_preset_blacknwhite, new WatchTheme(0xff000000, 0xffffffff, 0xfff0f0f0, 1.5f, 0.75f)),
        NIGHT_STAR(R.string.theme_preset_night_star, new WatchTheme(0xff340067, 0xff6a72a7, 0xffc5d1f1, 1.7f, 0.4f)),
        PSYCHEDELIC(R.string.theme_preset_psychedelic, new WatchTheme(0xff7339d3, 0xffc113da, 0xffffad00, 1.f, 0.18f)),
        BLUE_TRIANGLES(R.string.theme_preset_blue_triangles, new WatchTheme(0xff1078b6, 0xff71a0d0, 0xffbaddff, 5.f, 0.58f));

        public final int nameRes;
        public final WatchTheme theme;

        Preset(@StringRes int nameRes, WatchTheme theme) {
            this.nameRes = nameRes;
            this.theme = theme;
        }

        public static Preset fromName(String name) {
            Preset preset = null;

            if (name != null) {
                try {
                    preset = Preset.valueOf(name);
                } catch (IllegalArgumentException e) {
                    Log.w(Preset.class.getSimpleName(), "Can't find Preset with name: " + name, e);
                }
            }

            if (preset == null) {
                preset = Preset.DEFAULT;
            }
            return preset;
        }
    }
}
