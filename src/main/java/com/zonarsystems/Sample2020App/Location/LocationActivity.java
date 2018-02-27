package com.zonarsystems.Sample2020App.Location;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.zonarsystems.Sample2020App.R;

import java.util.ArrayList;

/**
 * Listens to location update from the 2020 GPS provider.
 * Note that the 2020 gets its GPS location from the V3.
 */
public class LocationActivity extends Activity {

    private LocationManager lm;
    private MyLocationListener listener;

    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Handler handler;
    private Runnable r;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        // Creates header with buttons
        final LinearLayout ll = new LinearLayout(this);
        final Button buttonLastKnown = createButton("Get Last Known Location");
        ll.addView(buttonLastKnown);
        final Button buttonClear = createButton("Clear List");
        ll.addView(buttonClear);

        setupListView(ll);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new MyLocationListener();

        //before requesting location updates, ensure that the GPS provider is enabled
        //It is not enabled until after first tablet dock
        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, listener);

            getLastKnowLocationPeriodically();

            setupButtonLastKnow(buttonLastKnown);
        }
        else {  //if the GPS provider is not enabled, prompt user to correct.
            addAndLog("Unable to connect to GPS.  Is the tablet docked?");
        }

        setupButtonClear(buttonClear);

    }

    private void setupButtonLastKnow(Button buttonLastKnown) {
        buttonLastKnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logLastKnowLocation();
            }
        });
    }

    private void setupButtonClear(Button buttonClear) {
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getLastKnowLocationPeriodically() {
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {

                logLastKnowLocation();
                handler.postDelayed(this, DateUtils.MINUTE_IN_MILLIS);

            }
        };
        handler.post(r);
    }

    private void setupListView(LinearLayout ll) {
        final ListView lv = (ListView) findViewById(R.id.lv);
        lv.addHeaderView(ll);
        lv.setAdapter(adapter);
    }

    private Button createButton(String text) {
        final Button button = new Button(this);
        button.setText(text);
        return button;
    }

    private void logLastKnowLocation() {
        final Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        addAndLog("lastKnownLocation: " + lastKnownLocation);
    }

    private void addAndLog(String text) {
        list.add(text);
        adapter.notifyDataSetChanged();
        Log.d("LocationTester", text);
    }


    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            addAndLog("onLocationChanged: " + location);
        }

        @Override
        public void onStatusChanged(String provider, int i, Bundle bundle) {
            addAndLog("onStatusChanged Provider: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            addAndLog("onProviderEnabled Provider: " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            addAndLog("onProviderDisabled Provider: " + provider);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            lm.removeUpdates(listener);
        }
        if (handler != null) {
            handler.removeCallbacks(r);
        }
    }
}
