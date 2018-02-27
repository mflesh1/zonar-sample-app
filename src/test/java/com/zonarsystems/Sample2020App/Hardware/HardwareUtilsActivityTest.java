package com.zonarsystems.Sample2020App.Hardware;

import com.zonarsystems.Sample2020App.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HardwareUtilsActivityTest {
  @Test
  public void testBasic() throws Exception {
    Robolectric.buildActivity(HardwareUtilsActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
  }
}
