package com.zonarsystems.Sample2020App.VehicleDeviceInfo;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.vehicledevice.VehicleDeviceInfo;
import com.zonarsystems.twenty20.sdk.vehicledevice.VehicleDeviceManager;
import com.zonarsystems.twenty20.sdk.vehicledevice.VehicleDeviceStatus;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Demonstrates how to get vehicle device info on Async thread.
 * <p/>
 * Also shows how to subscribe to the motion changes broadcast
 */
public class VehicleDeviceInfoActivity extends ListActivity {

    private BroadcastReceiver receiverMotionChanged;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fetchOnAsyncThread();

        handleRefreshButton();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerMotionChangedReceiver();
    }

    /**
     * onReceive will be called whenever motion changes above below a certain threshold
     */
    private void registerMotionChangedReceiver() {
        receiverMotionChanged = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                new VehicleDeviceInfoAsyncTask(VehicleDeviceInfoActivity.this) {
                    @Override
                    protected void onPostExecute(VehicleDeviceInfo vehicleDeviceInfo) {
                        Toast.makeText(VehicleDeviceInfoActivity.this, "Motion changed isMoving: " + vehicleDeviceInfo.isVehicleMoving(), Toast.LENGTH_LONG).show();
                        update(vehicleDeviceInfo);
                    }
                }.execute();
            }
        };
        registerReceiver(receiverMotionChanged, new IntentFilter(VehicleDeviceStatus.ACTION_VEHICLE_MOTION));
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (receiverMotionChanged != null) {
            unregisterReceiver(receiverMotionChanged);
        }
    }

    private void handleRefreshButton() {
        final Button button = new Button(this);
        button.setText(R.string.refresh);
        getListView().addHeaderView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchOnAsyncThread();
            }
        });
    }


    /**
     * Get vehicle device info on async thread as the call goes cross-process and could take some time to complete (i.e. don't tie
     * up the UI thread) or you could end up with ANRs.
     */
    private void fetchOnAsyncThread() {
        new VehicleDeviceInfoAsyncTask(this) {
            @Override
            protected VehicleDeviceInfo doInBackground(Void... voids) {
                VehicleDeviceManager vehicleDeviceManager = new VehicleDeviceManager(VehicleDeviceInfoActivity.this);
                return vehicleDeviceManager.getVehicleDeviceInfo();
            }

            /**
             * Update UI onPostExecute
             */
            @Override
            protected void onPostExecute(VehicleDeviceInfo info) {

                update(info);

            }
        }.execute();
    }

    private void update(VehicleDeviceInfo info) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Odometer: " + info.getOdometerMiles());
        strings.add(String.format("GPS Status: %s", info.getGPSLinkStatus() ? "Connected" : "N/A"));
        strings.add(String.format("GSM Status: %s", info.getGSMLinkStatus() ? "Connected" : "N/A"));
        strings.add(String.format(Locale.US, "Bearing: %s (Heading: %.2f)", info.getBearing(), info.getHeadingInDecimalDegrees()));
        strings.add(String.format(Locale.US, "Speed: %d", info.getSpeedInMph()));
        strings.add("Is Vehicle Moving? " + (info.isVehicleMoving() ? "Y" : "N"));
        strings.add(String.format(Locale.US, "In Motion Speed Threshold: %d", info.getInMotionSpeedThresholdMillimetersPerSecond()));
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
    }

    class VehicleDeviceInfoAsyncTask extends AsyncTask<Void, Void, VehicleDeviceInfo> {
        private VehicleDeviceInfoActivity vehicleDeviceInfoActivity;

        public VehicleDeviceInfoAsyncTask(VehicleDeviceInfoActivity vehicleDeviceInfoActivity) {
            this.vehicleDeviceInfoActivity = vehicleDeviceInfoActivity;
        }

        @Override
        protected VehicleDeviceInfo doInBackground(Void... voids) {
            VehicleDeviceManager vehicleDeviceManager = new VehicleDeviceManager(vehicleDeviceInfoActivity);
            return vehicleDeviceManager.getVehicleDeviceInfo();
        }
    }
}
