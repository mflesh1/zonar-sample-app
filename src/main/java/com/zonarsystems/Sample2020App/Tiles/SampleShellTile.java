package com.zonarsystems.Sample2020App.Tiles;

import android.content.Context;
import android.widget.RemoteViews;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.tiles.ShellTile;

/**
 * Tile that is hosted in Shell's Home Screen. This tile has no secondary tile.
 *
 * Tile is sent to Shell as a remote view.
 *
 * The Shell Tile implementation is similar to that of an Android widget. The application creates the view as a remote
 * view which is then sent to the shel
 */
public class SampleShellTile extends ShellTile {
    public SampleShellTile(Context context) {
        super(context, R.layout.shell_tile_primary);
    }


    public void setText(String statusText) {

        remoteViews.setTextViewText(R.id.status, statusText);

    }

    @Override
    protected RemoteViews getRemoteViewsSecondary() {
        return null;
    }
}
