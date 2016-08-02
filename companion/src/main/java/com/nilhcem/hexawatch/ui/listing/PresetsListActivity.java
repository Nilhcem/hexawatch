package com.nilhcem.hexawatch.ui.listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nilhcem.hexawatch.ui.BaseActivity;

public class PresetsListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout layout = new FrameLayout(this);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(recyclerView);
        setContentView(layout);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        PresetsListAdapter adapter = new PresetsListAdapter();
        recyclerView.setAdapter(adapter);
    }
}
