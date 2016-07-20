package com.nilhcem.hexawatch.common.ui;

import android.content.Context;

import com.nilhcem.hexawatch.common.ui.painter.Painter;

public class HexawatchCircle extends BaseHexawatch {

    HexawatchCircle(Context context, int width, int height, int strokeWidth, int paddingWidth, float innerHexaRatio, Painter painter) {
        super(context, width, height, strokeWidth, paddingWidth, innerHexaRatio, painter);
    }
}
