package com.zonarsystems.Sample2020App.VehicleDeviceInfo;

import com.zonarsystems.Sample2020App.BetterRoboCursor;
import com.zonarsystems.Sample2020App.BuildConfig;
import com.zonarsystems.twenty20.sdk.vehicledevice.VehicleDeviceManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class VehicleDeviceInfoActivityTest {
  @Test
  public void testBasic() throws Exception {
    final ShadowContentResolver shadowContentResolver = shadowOf(RuntimeEnvironment.application.getContentResolver());
    BetterRoboCursor cursor = new BetterRoboCursor();
    cursor.setColumnNames(VehicleDeviceManager.Columns.ALL);
    cursor.setResults(new Object[][] {{1234L,
            54, 57,
            98765L, 123L, 0, 4444L,
            8,
            848484L, 1,
            939393L, 2,
            767676L,
            1,
            555,
            0}});
    shadowContentResolver.setCursor(VehicleDeviceManager.URI, cursor);

    Robolectric.buildActivity(VehicleDeviceInfoActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
    //TODO: Send motion changed broadcast
  }
}
