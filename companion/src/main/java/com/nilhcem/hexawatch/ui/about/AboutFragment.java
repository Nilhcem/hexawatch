package com.nilhcem.hexawatch.ui.about;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nilhcem.hexawatch.BuildConfig;
import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.ui.BaseFragment;
import com.nilhcem.hexawatch.ui.widget.HexawatchView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutFragment extends BaseFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String STATE_KEY_HOUR = "hour";
    private static final String STATE_KEY_MINUTE = "minute";

    @BindView(R.id.about_version) TextView version;
    @BindView(R.id.about_watchface) HexawatchView watchface;
    @BindView(R.id.about_time) TextView time;

    private int hour;
    private int minute;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        version.setText(getString(R.string.about_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        watchface.setTheme(WatchTheme.Preset.FRANCE.theme);

        if (savedInstanceState == null) {
            setTime(3, 35);
        } else {
            setTime(savedInstanceState.getInt(STATE_KEY_HOUR), savedInstanceState.getInt(STATE_KEY_MINUTE));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_KEY_HOUR, hour);
        outState.putInt(STATE_KEY_MINUTE, minute);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setTime(hourOfDay, minute);
    }

    @OnClick(R.id.about_change_time_button)
    public void openTimePicker() {
        TimePickerDialogFragment.show(getChildFragmentManager(), hour, minute);
    }

    private void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;

        watchface.setTime(hour, minute);
        time.setText(String.format(Locale.US, "%02d:%02d", hour, minute));
    }
}
