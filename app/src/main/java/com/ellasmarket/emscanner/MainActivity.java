package com.ellasmarket.emscanner;

/*
 * Copyright ? 2011 Honeywell International Inc.
 *
 * Before run this sample, please install "D7800 Runtime Package" & "D7800 Tools Package" first, thanks!
 *
 * This sample shows you how to use intent to start scan and get the result.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.honeywell.scanintent.ScanIntent;

public class MainActivity extends Activity {
    TextView barcodeData = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        barcodeData = (TextView) findViewById(R.id.data);
    }

    public void onSendButtonClick(View v) {

        Intent intentScan = new Intent(ScanIntent.SCAN_ACTION);
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentScan.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        //int loadmode = 0;

        intentScan.putExtra("scan_mode", ScanIntent.SCAN_MODE_RESULT_AS_URI);

/*		SCAN_MODE_SHOW_NO_RESULT = 0;
		SCAN_MODE_SHOW_RESULT_UI = 1;
		SCAN_MODE_SHARE_BY_SMS = 2;
		SCAN_MODE_SHARE_BY_MMS = 3;
		SCAN_MODE_SHARE_BY_EMAIL = 4;
		SCAN_MODE_RESULT_AS_URI = 5;*/

        this.startActivityForResult(intentScan, 5);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == ScanIntent.SCAN_RESULT_SUCCESSED) {
            String data = intent
                    .getStringExtra(ScanIntent.EXTRA_RESULT_BARCODE_DATA);
            int format = intent.getIntExtra(
                    ScanIntent.EXTRA_RESULT_BARCODE_FORMAT, 0);

            barcodeData.setText(ScanIntent.EXTRA_RESULT_BARCODE_DATA+ ": " + data + "\r\n" + ScanIntent.EXTRA_RESULT_BARCODE_FORMAT + ": " + format);
        }
        else
            barcodeData.setText(R.string.scan_failed);
    }
}