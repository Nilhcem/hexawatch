package com.nilhcem.hexawatch.common.utils;

import android.content.Context;
import android.util.TypedValue;

public class ContextUtils {

    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
