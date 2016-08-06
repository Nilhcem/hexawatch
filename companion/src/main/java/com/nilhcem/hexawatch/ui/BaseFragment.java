package com.nilhcem.hexawatch.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nilhcem.hexawatch.common.config.ConfigHelper;
import com.nilhcem.hexawatch.core.AppPreferences;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private SharedPreferences.OnSharedPreferenceChangeListener onAppPrefsChangedListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (AppPreferences.KEY_IS_CIRCLE.equals(key)) {
                        onPreviewShapeChanged(appPrefs.isPreviewCircle());
                    }
                }
            };


    protected ConfigHelper configHelper;
    protected AppPreferences appPrefs;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        configHelper = new ConfigHelper(context);
        appPrefs = new AppPreferences(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        appPrefs.registerOnSharedPreferenceChangeListener(onAppPrefsChangedListener);
    }

    @Override
    public void onPause() {
        appPrefs.unregisterOnSharedPreferenceChangeListener(onAppPrefsChangedListener);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    protected void onPreviewShapeChanged(boolean isCircle) {
        // Hook to override if you need a callback when user changed the shape of the watchface
    }
}
