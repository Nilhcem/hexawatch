package com.nilhcem.hexawatch.ui.custom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.utils.ContextUtils;

public class ColorChooserView extends AppCompatTextView implements View.OnClickListener {

    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

    private static final float COMPOUND_DRAWABLE_PADDING_DP = 8f;
    private static final float CIRCLE_SIZE_DP = 32f;

    private final Drawable circle;

    private int color;
    private OnColorSelectedListener listener;

    public ColorChooserView(Context context) {
        this(context, null);
    }

    public ColorChooserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorChooserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.custom_text_size));
        int padding = getResources().getDimensionPixelSize(R.dimen.custom_default_padding);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(padding, padding, padding, padding);

        // Left circle
        int size = ContextUtils.dpToPx(context, CIRCLE_SIZE_DP);
        circle = ContextCompat.getDrawable(context, R.drawable.circle_drawable);
        circle.setBounds(0, 0, size, size);
        setCompoundDrawables(circle, null, null, null);
        setCompoundDrawablePadding(ContextUtils.dpToPx(context, COMPOUND_DRAWABLE_PADDING_DP));

        setOnClickListener(this);

        // Add ripple effect on click
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    @Override
    public void onClick(View view) {
        ColorPickerDialogBuilder.with(getContext())
                .initialColor(this.color)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .lightnessSliderOnly()
                .setTitle(getText().toString())
                .setPositiveButton(getContext().getString(R.string.ok), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        setColor(selectedColor);
                        if (listener != null) {
                            listener.onColorSelected(selectedColor);
                        }
                    }
                })
                .setNegativeButton(getContext().getString(R.string.cancel), null)
                .build()
                .show();
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }

    public void setColor(int color) {
        this.color = color;
        circle.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }
}
