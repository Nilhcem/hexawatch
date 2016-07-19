package com.nilhcem.hexawatch.common;

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

    class Builder {

        private Context context;
        private int shape;
        private int bgColor;
        private int strokeColor;
        private int fillColor;
        private int width;
        private int height;
        private int strokeWidth;
        private int marginWidth;
        private boolean ambient;
        private boolean lowBitAmbient;

        public Builder(Context context) {
            this.context = context;
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

        public Builder shape(@Shape int shape) {
            this.shape = shape;
            return this;
        }

        public Hexawatch build() {
            checkNotNull(shape, "You must specify the shape");
            checkArgument(width != 0 && height != 0, "You must specify the size (width and height)");
            checkArgument(strokeColor != 0 && fillColor != 0, "You must specify a bgColor and a fillColor");

            if (strokeWidth == 0) {
                strokeWidth(ambient ? 1f : 2f, UNIT_DP);
            }
            if (bgColor == 0) {
                bgColor = Color.TRANSPARENT;
            }

            if (shape == SHAPE_CIRCLE) {
                return new HexawatchCircleDrawer(context, width, height, strokeWidth, marginWidth, bgColor, strokeColor, fillColor, ambient, lowBitAmbient);
            } else {
                return null;
            }
        }
    }
}
