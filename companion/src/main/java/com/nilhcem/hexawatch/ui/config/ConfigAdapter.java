package com.nilhcem.hexawatch.ui.config;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.ui.custom.CustomFragment;
import com.nilhcem.hexawatch.ui.presets.PresetsFragment;

public class ConfigAdapter extends FragmentPagerAdapter {

    private static final int[] TITLES = {R.string.config_tab_themes, R.string.config_tab_custom};

    private final Context context;

    public ConfigAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PresetsFragment();
        }
        return new CustomFragment();
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(TITLES[position]);
    }
}
