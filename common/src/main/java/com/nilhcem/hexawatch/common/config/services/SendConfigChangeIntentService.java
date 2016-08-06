package com.nilhcem.hexawatch.common.config.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.nilhcem.hexawatch.common.R;
import com.nilhcem.hexawatch.common.config.SharedConfig;
import com.nilhcem.hexawatch.common.config.services.model.proto.Config;
import com.nilhcem.hexawatch.common.core.WatchTheme;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SendConfigChangeIntentService extends IntentService {

    public SendConfigChangeIntentService() {
        super(SendConfigChangeIntentService.class.getSimpleName());
    }

    public static void start(Context context) {
        context.startService(new Intent(context, SendConfigChangeIntentService.class));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(Wearable.API).build();
        ConnectionResult connectionResult = googleApiClient.blockingConnect(5, TimeUnit.SECONDS);

        if (connectionResult.isSuccess()) {
            try {
                sendConfigToNodes(googleApiClient, getSerializedConfig(), getNodesIds(googleApiClient));
            } finally {
                googleApiClient.disconnect();
            }
        } else {
            Log.w(getClass().getSimpleName(), "Failed to connect to GoogleApiClient: " + connectionResult.getErrorCode());
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

    private void sendConfigToNodes(GoogleApiClient googleApiClient, byte[] config, Collection<String> nodesIds) {
        MessageApi.SendMessageResult result;
        String path = getString(R.string.config_data_layer_path);

        for (String nodeId : nodesIds) {
            result = Wearable.MessageApi.sendMessage(googleApiClient, nodeId, path, config).await();
            if (!result.getStatus().isSuccess()) {
                Log.e(getClass().getSimpleName(), "Failed sending message to node: " + nodeId + ".\n" + result.getStatus().getStatusMessage());
            }
        }
    }

    private byte[] getSerializedConfig() {
        SharedConfig config = new SharedConfig(this);
        WatchTheme.Preset themePreset = config.getThemePreset();
        WatchTheme customTheme = config.getCustomTheme();

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
