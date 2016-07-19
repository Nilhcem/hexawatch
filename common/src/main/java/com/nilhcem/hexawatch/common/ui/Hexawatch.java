package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.TypedValue;

import com.nilhcem.hexawatch.common.utils.ContextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.nilhcem.hexawatch.common.utils.Preconditions.checkArgument;
import static com.nilhcem.hexawatch.common.utils.Preconditions.checkNotNull;

public interface Hexawatch {

    int SHAPE_CIRCLE = 0;
    int SHAPE_SQUARE = 1;
    int UNIT_DP = TypedValue.COMPLEX_UNIT_DIP;
    int UNIT_PX = TypedValue.COMPLEX_UNIT_PX;

    void drawTime(Canvas canvas, int hours, int minutes);

    @IntDef({SHAPE_CIRCLE, SHAPE_SQUARE})
    @Retention(RetentionPolicy.SOURCE) @interface Shape {
    }

    @IntDef({UNIT_DP, UNIT_PX})
    @Retention(RetentionPolicy.SOURCE) @interface Unit {
    }

    enum ColorPreset {

        BLACK(0xff333333, 0xffe6e6e6, 0xffb9b9b9),
        PINK(0xff952261, 0xfff1e5eb, 0xffcd79a6),
        BLUE(0xff387b94, 0xffebddd4, 0xff1f6179);

        int bgColor;
        int strokeColor;
        int fillColor;

        ColorPreset(int bgColor, int strokeColor, int fillColor) {
            this.bgColor = bgColor;
            this.strokeColor = strokeColor;
            this.fillColor = fillColor;
        }
    }

    class Builder {

        private Context context;
        private int shape;
        private int width;
        private int height;
        private int strokeWidth;
        private int marginWidth;
        private float innerHexaRatio = 0.75f;
        private int bgColor = Color.TRANSPARENT;
        private int strokeColor;
        private int fillColor;
        private boolean ambient;
        private boolean lowBitAmbient;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder shape(@Shape int shape) {
            this.shape = shape;
            return this;
        }

        public Builder size(float size, @Unit int unit) {
            return size(size, size, unit);
        }

        public Builder size(float width, float height, @Unit int unit) {
            if (unit == UNIT_DP) {
                this.width = ContextUtils.dpToIntPx(context, width);
                this.height = ContextUtils.dpToIntPx(context, height);
            } else {
                this.width = Math.round(width);
                this.height = Math.round(height);
            }
            return this;
        }

        public Builder strokeWidth(float strokeWidth, @Unit int unit) {
            if (unit == UNIT_DP) {
                this.strokeWidth = ContextUtils.dpToIntPx(context, strokeWidth);
            } else {
                this.strokeWidth = Math.round(strokeWidth);
            }
            return this;
        }

        public Builder marginWidth(float marginWidth, @Unit int unit) {
            if (unit == UNIT_DP) {
                this.marginWidth = ContextUtils.dpToIntPx(context, marginWidth);
            } else {
                this.marginWidth = Math.round(marginWidth);
            }
            return this;
        }

        public Builder innerHexaRatio(float innerHexaRatio) {
            this.innerHexaRatio = innerHexaRatio;
            return this;
        }

        public Builder colorPreset(ColorPreset colorPreset) {
            bgColor = colorPreset.bgColor;
            strokeColor = colorPreset.strokeColor;
            fillColor = colorPreset.fillColor;
            return this;
        }

        public Builder bgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public Builder strokeColor(int strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        public Builder fillColor(int fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public Builder ambient() {
            ambient = true;
            lowBitAmbient = false;

            strokeColor = 0xff666666;
            fillColor = 0xffdddddd;

            return this;
        }

        public Builder lowBitAmbient() {
            ambient = true;
            lowBitAmbient = true;

            strokeColor = Color.WHITE;
            fillColor = Color.WHITE;

            return this;
        }

        public Hexawatch build() {
            checkNotNull(shape, "You must specify the shape");
            checkArgument(width != 0 && height != 0, "You must specify the size (width and height)");
            checkArgument(strokeColor != 0 && fillColor != 0, "You must specify a colorPreset, or a bgColor+fillColor");

            if (strokeWidth == 0) {
                strokeWidth(ambient ? 1f : 2f, UNIT_DP);
            }

            if (shape == SHAPE_CIRCLE) {
                return new HexawatchCircle(context, width, height, strokeWidth, marginWidth, innerHexaRatio, bgColor, strokeColor, fillColor, ambient, lowBitAmbient);
            } else {
                return new HexawatchSquare(context, width, height, strokeWidth, marginWidth, innerHexaRatio, bgColor, strokeColor, fillColor, ambient, lowBitAmbient);
            }
        }
    }
}
