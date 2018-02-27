package com.zonarsystems.Sample2020App;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.res.builder.RobolectricPackageManager;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
  @Test
  public void testBasic() throws Exception {

    final RobolectricPackageManager pm = RuntimeEnvironment.getRobolectricPackageManager();
    PackageInfo p = pm.getPackageInfo("com.zonarsystems.Sample2020App", 0);
    ActivityInfo other = new ActivityInfo();
    other.nonLocalizedLabel = "blah";
    other.name = "xxx";
    p.activities = new ActivityInfo[]{new ActivityInfo(), other};
    pm.addPackage(p);

    ActivityController<MainActivity> c = Robolectric.buildActivity(MainActivity.class);
    c.create().start().resume().visible();

    c.get().onListItemClick(null, null, 0, 0);
    assertEquals(new Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .setComponent(new ComponentName(c.get(), other.name)),
            shadowOf(c.get()).getNextStartedActivity());

    c.pause().stop().destroy();
  }
}
