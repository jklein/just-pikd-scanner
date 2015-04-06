package com.ellasmarket.emscanner.activity;

/*
 * Copyright G2G Market Inc, 2015
 */


import android.os.Bundle;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.fragment.ScanFragment;

public class ReceivingActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
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
    }
}