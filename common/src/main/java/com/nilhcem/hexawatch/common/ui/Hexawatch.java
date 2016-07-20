package com.nilhcem.hexawatch.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.IntDef;
import android.util.TypedValue;

import com.nilhcem.hexawatch.common.core.WatchShape;
import com.nilhcem.hexawatch.common.ui.painter.Painter;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.nilhcem.hexawatch.common.utils.Preconditions.checkArgument;
import static com.nilhcem.hexawatch.common.utils.Preconditions.checkNotNull;

public interface Hexawatch {

    int UNIT_DP = TypedValue.COMPLEX_UNIT_DIP;
    int UNIT_PX = TypedValue.COMPLEX_UNIT_PX;

    void drawTime(Canvas canvas, int hours, int minutes);

    @IntDef({UNIT_DP, UNIT_PX})
    @Retention(RetentionPolicy.SOURCE) @interface Unit {
    }

    class Builder {

        private Context context;
        private Painter painter;
        private WatchShape shape;
        private int width;
        private int height;
        private int strokeWidth;
        private int marginWidth;
        private float innerHexaRatio = 0.75f;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder painter(Painter painter) {
            this.painter = painter;
            return this;
        }

        public Builder shape(WatchShape shape) {
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

        public Hexawatch build() {
            checkNotNull(shape, "You must specify the shape");
            checkArgument(width != 0 && height != 0, "You must specify the size (width and height)");

            if (shape == WatchShape.CIRCLE) {
                return new HexawatchCircle(context, width, height, strokeWidth, marginWidth, innerHexaRatio, painter);
            } else {
                return new HexawatchSquare(context, width, height, strokeWidth, marginWidth, innerHexaRatio, painter);
            }
        }
    }
}
