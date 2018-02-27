package com.zonarsystems.Sample2020App.Tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * This receiver is triggered periodically when the Shell's wants to update its home screen.
 * When its triggered it means that the Shell Home screen wants an update (like when the shell screen is resumed).
 *
 * See the AndroidManifest.xml
 */
public class TileRequestUpdateReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        //TODO: uncomment to show primary without secondary
        //updateTilePrimaryOnly(context);

        //TODO: comment to show show primary without secondary
        updateTileWithSecondary(context);

    }

    private void updateTilePrimaryOnly(Context context) {
        SampleShellTile tilePrimaryOnly = new SampleShellTile(context);
        tilePrimaryOnly.setText("Updated by receiver: " + new Date().toLocaleString());
        tilePrimaryOnly.send(false);
    }

    private void updateTileWithSecondary(Context context) {
        SampleShellTilePlusSecondary tileWithSecondary = new SampleShellTilePlusSecondary(context);
        tileWithSecondary.setText("Updated by receiver: " + new Date().toLocaleString());
        tileWithSecondary.send(false);
    }
}
