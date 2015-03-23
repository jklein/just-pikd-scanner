package com.ellasmarket.emscanner.activity;

/*
 * Copyright G2G Market Inc, 2015
 */


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.fragment.ScanFragment;

public class MainActivity extends BaseActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lock screen orientation to portrait so that screen doesn't rotate during operations
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        scanFragment = ((ScanFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_scan));
        scanFragment.setHint("Scan or Enter PO#");

        /* disable bluetooth for now
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothChatFragment fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
        */
        //Toast.makeText(getApplicationContext(),s,
        //        Toast.LENGTH_SHORT).show();
    }
/*
    public void onSendButtonClick(View v) {
        startScan();
    }

    //this, as well as the onKeyDown thing, should likely be factored out into another class
    public void startScan() {
        Intent intentScan = new Intent(ScanIntent.SCAN_ACTION);
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        //int loadmode = 0;

        intentScan.putExtra("scan_mode", ScanIntent.SCAN_MODE_RESULT_AS_URI);

		SCAN_MODE_SHOW_NO_RESULT = 0;
		SCAN_MODE_SHOW_RESULT_UI = 1;
		SCAN_MODE_SHARE_BY_SMS = 2;
		SCAN_MODE_SHARE_BY_MMS = 3;
		SCAN_MODE_SHARE_BY_EMAIL = 4;
		SCAN_MODE_RESULT_AS_URI = 5;
        this.startActivityForResult(intentScan, 5);
    }
    */


}