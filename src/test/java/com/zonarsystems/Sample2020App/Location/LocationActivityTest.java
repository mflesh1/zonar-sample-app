package com.zonarsystems.Sample2020App.Location;

import android.content.Context;
import android.location.LocationManager;

import com.zonarsystems.Sample2020App.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLocationManager;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class LocationActivityTest {
  @Test
  public void testBasic() throws Exception {
    final ShadowLocationManager shadowLocationManager = shadowOf((LocationManager)
            RuntimeEnvironment.application.getSystemService(Context.LOCATION_SERVICE));
    shadowLocationManager.setProviderEnabled(LocationManager.GPS_PROVIDER, true);
    Robolectric.buildActivity(LocationActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
  }
}
