package com.nilhcem.hexawatch.ui.config;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.nilhcem.hexawatch.R;
import com.nilhcem.hexawatch.config.AppConfig;
import com.nilhcem.hexawatch.ui.BaseActivity;

import butterknife.BindView;

public class ConfigActivity extends BaseActivity {

    @BindView(R.id.config_tablayout) TabLayout tabLayout;
    @BindView(R.id.config_viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new ConfigAdapter(this, getSupportFragmentManager()));

        if (new AppConfig(this).isFirstLaunch()) {
            viewPager.setCurrentItem(2);
        }
    }
}
