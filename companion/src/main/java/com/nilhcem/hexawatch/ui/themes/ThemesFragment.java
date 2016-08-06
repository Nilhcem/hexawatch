package com.nilhcem.hexawatch.ui.themes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.common.core.WatchTheme;
import com.nilhcem.hexawatch.common.utils.ContextUtils;
import com.nilhcem.hexawatch.ui.BaseFragment;
import com.nilhcem.hexawatch.ui.widget.recyclerview.MarginDecoration;

import butterknife.BindView;

public class ThemesFragment extends BaseFragment implements ThemesAdapter.OnThemePresetSelectedListener {

    @BindView(R.id.presets_recyclerview) RecyclerView recyclerView;

    private ThemesAdapter adapter;
    private SharedPreferences.OnSharedPreferenceChangeListener customThemeChangedListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    adapter.updateTheme(configHelper.getThemePreset(), configHelper.getCustomTheme());
                }
            };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.themes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ThemesAdapter(appPrefs.isPreviewCircle(), configHelper.getCustomTheme(), configHelper.getThemePreset(), this);

        recyclerView.addItemDecoration(new MarginDecoration(ContextUtils.dpToPx(getContext(), 4f)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        configHelper.registerOnSharedPreferenceChangeListener(customThemeChangedListener);
    }

    @Override
    public void onPause() {
        configHelper.unregisterOnSharedPreferenceChangeListener(customThemeChangedListener);
        super.onPause();
    }

    @Override
    public void onThemePresetSelected(WatchTheme.Preset preset) {
        configHelper.setThemePreset(preset);
    }

    @Override
    protected void onPreviewShapeChanged(boolean isCircle) {
        adapter.setCircleShape(isCircle);
    }
}
