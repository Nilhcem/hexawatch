package com.nilhcem.hexawatch.common.config.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.nilhcem.hexawatch.common.R;
import com.nilhcem.hexawatch.common.config.ConfigHelper;
import com.nilhcem.hexawatch.common.config.services.model.proto.Config;
import com.nilhcem.hexawatch.common.core.WatchTheme;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SendConfigChangeIntentService extends IntentService {

    public static void start(Context context) {
        context.startService(new Intent(context, SendConfigChangeIntentService.class));
    }

    public SendConfigChangeIntentService() {
        super(SendConfigChangeIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(Wearable.API).build();
        ConnectionResult connectionResult = googleApiClient.blockingConnect(5, TimeUnit.SECONDS);

        if (connectionResult.isSuccess()) {
            try {
                MessageApi.SendMessageResult result;
                String path = getString(R.string.config_data_layer_path);
                byte[] config = getSerializedConfig();

                for (String nodeId : getNodesIds(googleApiClient)) {
                    result = Wearable.MessageApi.sendMessage(googleApiClient, nodeId, path, config).await();
                    if (!result.getStatus().isSuccess()) {
                        // TODO log: failed to send message to node: nodeId
                    }
                }
            } finally {
                googleApiClient.disconnect();
            }
        } else {
//            Log.e(TAG, "Failed to connect to GoogleApiClient: " + connectionResult.getErrorCode());
        }
    }

    private Collection<String> getNodesIds(GoogleApiClient googleApiClient) {
        Collection<String> results = new HashSet<>();

        // Retrieve the nodes with the required capabilities
        CapabilityApi.GetCapabilityResult result = Wearable.CapabilityApi.getCapability(googleApiClient, getString(R.string.config_node_capability), CapabilityApi.FILTER_REACHABLE).await();
        Set<Node> nodes = result.getCapability().getNodes();
        for (Node node : nodes) {
            results.add(node.getId());
        }
        return results;
    }

    private byte[] getSerializedConfig() {
        ConfigHelper configHelper = new ConfigHelper(this);
        WatchTheme.Preset themePreset = configHelper.getThemePreset();
        WatchTheme customTheme = configHelper.getCustomTheme();

        return Config.Model.newBuilder()
                .setPreset(themePreset.name())
                .setBgColor(customTheme.bgColor)
                .setFillColor(customTheme.fillColor)
                .setStrokeColor(customTheme.strokeColor)
                .setStrokeWidth(customTheme.strokeWidthDp)
                .setInnerHexaRatio(customTheme.innerHexaRatio)
                .build().toByteArray();
    }
}
