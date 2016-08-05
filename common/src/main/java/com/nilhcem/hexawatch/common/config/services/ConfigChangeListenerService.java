package com.nilhcem.hexawatch.common.config.services;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.protobuf.InvalidProtocolBufferException;
import com.nilhcem.hexawatch.common.R;
import com.nilhcem.hexawatch.common.config.ConfigHelper;
import com.nilhcem.hexawatch.common.config.services.model.proto.Config;
import com.nilhcem.hexawatch.common.core.WatchTheme;

public class ConfigChangeListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);

        if (messageEvent.getPath().equals(getString(R.string.config_data_layer_path))) {
            try {
                Config.Model model = Config.Model.parseFrom(messageEvent.getData());
                WatchTheme.Preset preset = WatchTheme.Preset.valueOf(model.getPreset());
                WatchTheme theme = new WatchTheme(model.getBgColor(), model.getFillColor(), model.getStrokeColor(), model.getStrokeWidth(), model.getInnerHexaRatio());
                new ConfigHelper(this).setDataInternally(preset, theme);
            } catch (InvalidProtocolBufferException e) {
                Log.e(getClass().getSimpleName(), "Error receiving protobuf message", e);
            }
        }
    }
}
