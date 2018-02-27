package com.zonarsystems.Sample2020App.Tiles;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;

import com.zonarsystems.Sample2020App.Drivers.DriverActivity;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.tiles.ShellTileSecondary;

/**
 * Created with IntelliJ IDEA.
 * User: steve.fiedelberg
 * Date: 10/31/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleShellTileSecondary extends ShellTileSecondary {
    public SampleShellTileSecondary(Context context) {
        super(context, R.layout.shell_tile_secondary);
    }

    @Override
    protected PendingIntent createPendingIntent() {
        return getPendingIntent();
    }

    /**
     *
     * @return pending intent that launches an Activity
     */
    private PendingIntent getPendingIntent() {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, DriverActivity.class));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    @Override
    protected @IdRes Integer getClickViewId() {
        return R.id.secondary_click;

    }
}
