package com.nilhcem.hexawatch.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

public class WearFrameLayout extends ViewGroup {

    private final Paint paint;
    private final RectF strapRect = new RectF();
    private final RectF marginRect = new RectF();
    private final RectF borderRect = new RectF();
    private final Rect childRect = new Rect();
    private final PorterDuffXfermode clearMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private final int color;
    private final float marginWidth;
    private final float borderWidth;
    private final boolean isRound;

    private float marginRadius;
    private float borderRadius;

    public WearFrameLayout(Context context) {
        this(context, null);
    }

    public WearFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // We need to turn off hardware acceleration to be able to draw a clear Xfer on the canvas
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        // Get from attrs, or set manually
        isRound = true;
        color = 0xff909090;

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        marginWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, displayMetrics);
        borderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, displayMetrics);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() != 1) {
            throw new IllegalArgumentException("WearFrameLayout must have one single child");
        }

        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        float centerX = (float) width / 2;
        float centerY = (float) height / 2;

        // transparent margin
        marginRadius = (Math.min(width, height * 0.86f) * 0.9f) / 2;
        marginRect.set(centerX - marginRadius, centerY - marginRadius, centerX + marginRadius, centerY + marginRadius);

        // watch border
        borderRadius = marginRadius - marginWidth - (borderWidth / 2);
        borderRect.set(centerX - borderRadius, centerY - borderRadius, centerX + borderRadius, centerY + borderRadius);

        // watch-strap
        float halfStrapWidth = borderRadius / 2;
        strapRect.set(centerX - halfStrapWidth, 0, centerX + halfStrapWidth, height);

        // inner child
        float childRadius = borderRadius - borderWidth / 2;
        int childSize = Math.round(childRadius * 2);
        childRect.set(Math.round(centerX - childRadius), Math.round(centerY - childRadius), Math.round(centerX + childRadius), Math.round(centerY + childRadius));
        getChildAt(0).measure(MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY));

        // this
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            getChildAt(0).layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // watch-strap
        canvas.drawRect(strapRect, paint);

        // transparent watch margin
        paint.setXfermode(clearMode);
        canvas.drawRoundRect(marginRect, isRound ? marginRadius : 0, isRound ? marginRadius : 0, paint);
        paint.setXfermode(null);

        // watch border
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(borderRect, isRound ? borderRadius : marginWidth, isRound ? borderRadius : marginWidth, paint);
        paint.setStyle(Paint.Style.FILL);

        // sub views
        super.dispatchDraw(canvas);
    }
}
