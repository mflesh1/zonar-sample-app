package com.zonarsystems.Sample2020App.DeviceProfile;

import android.net.Uri;

import com.zonarsystems.Sample2020App.BetterRoboCursor;
import com.zonarsystems.Sample2020App.BuildConfig;
import com.zonarsystems.twenty20.sdk.deviceprofile.DeviceProfileManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.shadows.ShadowLog;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DeviceProfileActivityTest {

  @Test
  public void testBasic() throws Exception {
    ShadowLog.stream = System.out;
    final ShadowContentResolver shadowContentResolver = shadowOf(RuntimeEnvironment.application.getContentResolver());

    BetterRoboCursor cursor = new BetterRoboCursor();
    cursor.setColumnNames(new String[]{
            "accountCode",
            "customerName",
            "customerAddress",
            "dotNumber",
            "unitsOfMeasure",
            "defaultTimezone"
    });
    cursor.setResults(new Object[][]{{"TestAccountCode", "Mr. Customer", "On the moon",
            12345, "metric", "PST8PDT"}});

    shadowContentResolver.setCursor(
            Uri.parse(DeviceProfileManager.URI_DEVICE_PROFILE + "/organization_profile"), cursor);

    BetterRoboCursor cursor2 = new BetterRoboCursor();
    cursor2.setColumnNames(new String[]{
            "assetNumber",
            "carryingDangerousCargo",
            "ecuVin",
            "vin",
            "jurisdiction",
            "type",
            "licensePlateNum",
            "manufacturer",
            "tagNumber",
            "length",
            "height",
            "weight",
            "weightPerAxle",
            "width",
            "assetId",
            "mobileDeviceTypeId",
            "exsid",
    });
    cursor2.setResults(new Object[][] {{
            "fake asset number", "not dangerous cargo", "ecu vinny", "plain vinny",
            "United States", "Type 2", "License number 5", "George's Car Maker", "tag you are it",
            4.5f, 7.2f, 999.8f, 100.001f, 12345.4f,
            "asset I AM", 4, "external system id"}});
    shadowContentResolver.setCursor(
            Uri.parse(DeviceProfileManager.URI_DEVICE_PROFILE + "/asset_info"), cursor2);

    Robolectric.buildActivity(DeviceProfileActivity.class)
            .create().start().resume().visible()
            .pause().stop().destroy();
  }
}
