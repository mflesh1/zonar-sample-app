package com.zonarsystems.Sample2020App;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Launches activity with samples in them
 */
public class MainActivity extends ListActivity {


    private Page[] pages;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);

            final int length = packageInfo.activities.length;
            pages = new Page[length - 1];

            ActivityInfo[] activities = packageInfo.activities;
            for (int i = 1; i < length; i++) {
                ActivityInfo info = activities[i];
                pages[i - 1] = new Page(info);
            }

            setListAdapter(new ArrayAdapter<Page>(this, android.R.layout.simple_list_item_1, pages));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        pages[position].launch();

    }

    private class Page {
        private final ActivityInfo activityInfo;
        private String name = "";

        public Page(ActivityInfo activityInfo) {
            this.activityInfo = activityInfo;

            name = (String) activityInfo.loadLabel(getPackageManager());
        }

        @Override
        public String toString() {
            return name;
        }

        public void launch() {
            Intent appLaunchIntent = new Intent(Intent.ACTION_MAIN);
            appLaunchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            appLaunchIntent.setComponent(new ComponentName(MainActivity.this, activityInfo.name));
            startActivity(appLaunchIntent);
        }
    }
}