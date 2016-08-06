package com.nilhcem.hexawatch.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.applyDimension;

public class WearFrameLayout extends ViewGroup {

    private final Paint paint;
    private final RectF strapRect = new RectF();
    private final RectF marginRect = new RectF();
    private final RectF borderRect = new RectF();
    private final RectF buttonRect = new RectF();
    private final Rect childRect = new Rect();
    private final PorterDuffXfermode clearMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private final int color;
    private final float marginWidth;
    private final float borderWidth;
    private final float buttonWidth;
    private final float buttonHeight;

    private boolean isCircle;
    private boolean showButton;

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
        isCircle = true;
        color = 0xff909090;
        showButton = isCircle;

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        marginWidth = applyDimension(COMPLEX_UNIT_DIP, 4, displayMetrics);
        borderWidth = applyDimension(COMPLEX_UNIT_DIP, 8, displayMetrics);
        buttonWidth = applyDimension(COMPLEX_UNIT_DIP, 20, displayMetrics);
        buttonHeight = applyDimension(COMPLEX_UNIT_DIP, 20, displayMetrics);

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
        marginRadius = Math.min(width * 0.9f, height * 0.82f) / 2;
        marginRect.set(centerX - marginRadius, centerY - marginRadius, centerX + marginRadius, centerY + marginRadius);

        // watch border
        borderRadius = marginRadius - marginWidth - (borderWidth / 2);
        borderRect.set(centerX - borderRadius, centerY - borderRadius, centerX + borderRadius, centerY + borderRadius);

        // watch-strap
        float halfStrapWidth = borderRadius / 2;
        strapRect.set(centerX - halfStrapWidth, 0, centerX + halfStrapWidth, height);

        // button
        buttonRect.set(borderRect.right - buttonWidth, centerY - buttonHeight / 2, borderRect.right + buttonWidth, centerY + buttonHeight / 2);

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

        // button
        if (showButton) {
            canvas.drawRoundRect(buttonRect, marginWidth, marginWidth, paint);
        }

        // transparent watch margin
        paint.setXfermode(clearMode);
        canvas.drawRoundRect(marginRect, isCircle ? marginRadius : 0, isCircle ? marginRadius : 0, paint);
        paint.setXfermode(null);

        // sub views
        super.dispatchDraw(canvas);

        // watch border
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(borderRect, isCircle ? borderRadius : marginWidth, isCircle ? borderRadius : marginWidth, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setCircleShape(boolean isCircle) {
        this.isCircle = isCircle;
        showButton = isCircle;
        invalidate();
    }
}
