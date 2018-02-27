package com.zonarsystems.Sample2020App.Tiles;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.zonarsystems.Sample2020App.R;

/**
 * Information on how to get your tile to show up on 2020's Home Screen
 */
public class ShellTileActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_tile);

        final TextView text = (TextView) findViewById(R.id.textView);
        text.setText(R.string.directions);
    }
}
