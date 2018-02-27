package com.zonarsystems.Sample2020App.Tiles;

import android.content.Intent;

import com.zonarsystems.Sample2020App.BuildConfig;
import com.zonarsystems.twenty20.sdk.tiles.ShellTile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleShellTilePlusSecondaryTest {
  @Test
  public void testBasic() throws Exception {
    SampleShellTilePlusSecondary sut = new SampleShellTilePlusSecondary(RuntimeEnvironment.application);
    new TileRequestUpdateReceiver().onReceive(RuntimeEnvironment.application, null);
    assertEquals(ShellTile.ACTION_TILE_UPDATED, ShadowApplication.getInstance().getBroadcastIntents().get(0).getAction());
  }
}
