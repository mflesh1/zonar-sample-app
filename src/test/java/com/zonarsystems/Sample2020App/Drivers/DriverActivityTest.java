package com.zonarsystems.Sample2020App.Drivers;

import com.zonarsystems.Sample2020App.BetterRoboCursor;
import com.zonarsystems.Sample2020App.BuildConfig;
import com.zonarsystems.Sample2020App.DeviceProfile.DeviceProfileActivity;
import com.zonarsystems.twenty20.sdk.driver.DriverProfile;
import com.zonarsystems.twenty20.sdk.driver.DriverProfileManager;
import com.zonarsystems.twenty20.sdk.driver.HomeTerminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DriverActivityTest {
  @Test
  public void testBasic() throws Exception {
    BetterRoboCursor cursor = new BetterRoboCursor();
    cursor.setColumnNames(new String[]{
            DriverProfile.Columns.COL_DRIVER_ID, DriverProfile.Columns.COL_PIN,
            DriverProfile.Columns.COL_FIRST_NAME, DriverProfile.Columns.COL_LAST_NAME,
            DriverProfile.Columns.COL_CARRIER_NAME, DriverProfile.Columns.COL_CARRIER_ID,
            DriverProfile.Columns.COL_RULESET, DriverProfile.Columns.COL_HOME_TIME_ZONE,
            DriverProfile.Columns.COL_LICENSE_INFO, DriverProfile.Columns.COL_LOGIN_STATUS,
            DriverProfile.Columns.COL_IS_AUTHENTICATED, DriverProfile.Columns.COL_TAG_ID,
            DriverProfile.Columns.COL_SHIFT_START_TIME, DriverProfile.Columns.COL_LICENSE_JURD,
            DriverProfile.Columns.COL_LAST_LOGIN, DriverProfile.Columns.COL_EXTERNAL_ID,
            HomeTerminal.Columns.COL_NAME, HomeTerminal.Columns.COL_STREET,
            HomeTerminal.Columns.COL_CITY, HomeTerminal.Columns.COL_STATE,
            HomeTerminal.Columns.COL_COUNTRY, HomeTerminal.Columns.COL_ZIP,
            HomeTerminal.Columns.COL_COUNTY, HomeTerminal.Columns.COL_LATITUDE,
            HomeTerminal.Columns.COL_LONGITUDE
    });
    cursor.setResults(new Object[][]{
            {10, "4444", "First", "Last", "xx", "nnn", "Rule5", "PST", "what",
                    1, 1, 999, "foo", "CA", 5555L, "yy",
                    "Oh", "My St.", "Somewhere", "WA", "USA", "94222", "King", 44.2f, 66.1f},
            {20, "5555", "Bar", "Quux", "yy", "ooo", "Rule2", "EST", "ever",
                    2, 1, 888, "bar", "NV", 4444L, "xx",
                    "Oh", "My St.", "Somewhere", "WA", "USA", "94222", "King", 44.2f, 66.1f}
    });
    shadowOf(RuntimeEnvironment.application.getContentResolver())
            .setCursor(DriverProfileManager.getDriversUri(), cursor);

    Robolectric.buildActivity(DriverActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
  }
}
