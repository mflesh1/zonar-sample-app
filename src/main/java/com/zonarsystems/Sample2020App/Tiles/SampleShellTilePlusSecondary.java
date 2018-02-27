package com.zonarsystems.Sample2020App.Tiles;

import android.content.Context;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.tiles.ShellTilePlusSecondary;

/**
 * Tile that is hosted in Shell's Home Screen. This tile has a primary and a selectable secondary tile.
 *
 * These tiles are sent to Shell as remote views.
 *
 * The Shell Tile implementation is similar to that of an Android widget. The application creates the view as a remote
 * view which is then sent to the Shell.
 */
public class SampleShellTilePlusSecondary extends ShellTilePlusSecondary {
    public SampleShellTilePlusSecondary(Context context) {
        super(context, R.layout.shell_tile_primary, new SampleShellTileSecondary(context));
    }

    public void setText(String statusText) {
        remoteViews.setTextViewText(R.id.status, statusText);
    }
}
