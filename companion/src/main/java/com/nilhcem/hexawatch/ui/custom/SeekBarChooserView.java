package com.nilhcem.hexawatch.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nilhcem.hexawatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeekBarChooserView extends LinearLayout implements SeekBar.OnSeekBarChangeListener {

    public interface OnValueChangedListener {
        void onValueChanged(int value);
    }

    @BindView(R.id.range_chooser_title) TextView title;
    @BindView(R.id.range_chooser_seekbar) SeekBar seekbar;

    private OnValueChangedListener listener;

    public SeekBarChooserView(Context context) {
        this(context, null);
    }

    public SeekBarChooserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarChooserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        int padding = getResources().getDimensionPixelSize(R.dimen.custom_default_padding);
        setPadding(padding, padding, padding, padding);

        LayoutInflater.from(context).inflate(R.layout.custom_range_chooser, this, true);
        ButterKnife.bind(this, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBarChooserView);
        title.setText(a.getString(R.styleable.SeekBarChooserView_android_text));
        a.recycle();

        seekbar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null) {
            listener.onValueChanged(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Do nothing
    }

    public void setOnValueChangedListener(OnValueChangedListener listener) {
        this.listener = listener;
    }

    public void setValue(int value) {
        seekbar.setProgress(value);
    }
}
