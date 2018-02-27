package com.zonarsystems.Sample2020App.Tiles;

import com.zonarsystems.Sample2020App.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ShellTileActivityTest {
  @Test
  public void testBasic() throws Exception {
    Robolectric.buildActivity(ShellTileActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
  }
}
