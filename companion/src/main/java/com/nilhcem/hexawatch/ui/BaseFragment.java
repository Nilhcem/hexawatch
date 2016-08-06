package com.nilhcem.hexawatch.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nilhcem.hexawatch.common.config.SharedConfig;
import com.nilhcem.hexawatch.config.AppConfig;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected AppConfig appConfig;
    protected SharedConfig sharedConfig;

    private Unbinder unbinder;
    private final SharedPreferences.OnSharedPreferenceChangeListener onAppPrefsChangedListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (AppConfig.KEY_IS_CIRCLE.equals(key)) {
                        onPreviewShapeChanged(appConfig.isPreviewCircle());
                    }
                }
            };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appConfig = new AppConfig(context);
        sharedConfig = new SharedConfig(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        appConfig.registerOnSharedPreferenceChangeListener(onAppPrefsChangedListener);
    }

    @Override
    public void onPause() {
        appConfig.unregisterOnSharedPreferenceChangeListener(onAppPrefsChangedListener);
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
