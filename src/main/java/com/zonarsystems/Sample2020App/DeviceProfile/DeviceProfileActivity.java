package com.zonarsystems.Sample2020App.DeviceProfile;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.deviceprofile.AssetInfo;
import com.zonarsystems.twenty20.sdk.deviceprofile.DeviceProfileManager;
import com.zonarsystems.twenty20.sdk.deviceprofile.OrganizationProfile;

/**
 * Created with IntelliJ IDEA.
 * User: steve.fiedelberg
 * Date: 11/8/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceProfileActivity extends Activity {

    private TextView accountCodeView;
    private TextView assetNumberView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.device_profile);

        accountCodeView = (TextView) findViewById(R.id.accountCode);
        assetNumberView = (TextView) findViewById(R.id.assetNumber);

        new OrganizationProfileAsyncTask().execute();
        new AssetInfoAsyncTask().execute();
    }


    private class OrganizationProfileAsyncTask extends AsyncTask<Void, Void, OrganizationProfile> {
        @Override
        protected OrganizationProfile doInBackground(Void... voids) {

            return new DeviceProfileManager(DeviceProfileActivity.this).getOrganizationProfile();

        }

        @Override
        protected void onPostExecute(OrganizationProfile organizationProfile) {
            accountCodeView.setText(organizationProfile.getAccountCode());
        }
    }

    private class AssetInfoAsyncTask extends AsyncTask<Void, Void, AssetInfo> {
        @Override
        protected AssetInfo doInBackground(Void... voids) {

            return new DeviceProfileManager(DeviceProfileActivity.this).getAssetInfo();

        }

        @Override
        protected void onPostExecute(AssetInfo assetInfo) {
            assetNumberView.setText(assetInfo.assetNumber);
        }
    }
}