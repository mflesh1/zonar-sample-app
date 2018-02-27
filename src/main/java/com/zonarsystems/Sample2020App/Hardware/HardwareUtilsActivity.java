package com.zonarsystems.Sample2020App.Hardware;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.zonarsystems.Sample2020App.R;
import com.zonarsystems.twenty20.sdk.utils.HardwareUtils;

/**
 * Created with IntelliJ IDEA.
 * User: steve.fiedelberg
 * Date: 11/8/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class HardwareUtilsActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hardware);
        ((TextView) findViewById(R.id.docked)).setText(Boolean.toString(HardwareUtils.isDocked(this)));
    }


}