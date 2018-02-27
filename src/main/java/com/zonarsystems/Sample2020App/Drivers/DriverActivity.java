package com.zonarsystems.Sample2020App.Drivers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.driver.DriverProfile;
import com.zonarsystems.twenty20.sdk.driver.DriverProfileManager;
import com.zonarsystems.twenty20.sdk.driver.Drivers;

/**
 * Indicates how to fetch driver information on Async thread
 */
public class DriverActivity extends Activity {

    private TextView driverName;
    private TextView coDriverName;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivers);

        driverName = (TextView) findViewById(R.id.driver);
        coDriverName = (TextView) findViewById(R.id.codriver);

        fetchDriversOnAsyncThread();
    }


    /**
     * Get drivers on async thread as the call goes cross-process and could take some time to complete (i.e. don't tie
     * up the UI thread) or you could end up with ANRs.
     */
    private void fetchDriversOnAsyncThread() {
        new AsyncTask<Void, Void, Drivers>() {
            @Override
            protected Drivers doInBackground(Void... voids) {
                DriverProfileManager driverManager = new DriverProfileManager(DriverActivity.this);
                return driverManager.getLoggedInDrivers();
            }

            /**
             * Update UI onPostExecute
             */
            @Override
            protected void onPostExecute(Drivers drivers) {

                setText(driverName, drivers.getDriver(), "Driver");
                setText(coDriverName, drivers.getCoDriver(), "Co-driver");

            }

            private void setText(TextView driverName, DriverProfile driverProfile, String who) {
                driverName.setText(getString(R.string.who_with_status, who,
                        (driverProfile != null) ? driverProfile.getFullName() : "not logged in"));
            }
        }.execute();
    }
}
