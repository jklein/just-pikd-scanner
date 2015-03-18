package com.ellasmarket.emscanner.activity;

/*
 * Copyright G2G Market Inc, 2015
 */


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.bluetooth.BluetoothChatFragment;
import com.ellasmarket.emscanner.model.Spo;
import com.honeywell.scanintent.ScanIntent;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class MainActivity extends BaseActivity {
    TextView barcodeData = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lock screen orientation to portrait so that screen doesn't rotate during operations
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        barcodeData = (TextView) findViewById(R.id.data);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothChatFragment fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        //Toast.makeText(getApplicationContext(),s,
        //        Toast.LENGTH_SHORT).show();
    }

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

/*		SCAN_MODE_SHOW_NO_RESULT = 0;
		SCAN_MODE_SHOW_RESULT_UI = 1;
		SCAN_MODE_SHARE_BY_SMS = 2;
		SCAN_MODE_SHARE_BY_MMS = 3;
		SCAN_MODE_SHARE_BY_EMAIL = 4;
		SCAN_MODE_RESULT_AS_URI = 5;*/
        this.startActivityForResult(intentScan, 5);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //KEYCODE_UNKNOWN is the value that the hardware scan button on honeywell
        //devices ends up sending.
        if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
            startScan();
            //true to indicate we handled the key event
            return true;
        }
        //otherwise call super class's method to handle other buttons
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == ScanIntent.SCAN_RESULT_SUCCESSED) {
            String data = intent
                    .getStringExtra(ScanIntent.EXTRA_RESULT_BARCODE_DATA);
            int format = intent.getIntExtra(
                    ScanIntent.EXTRA_RESULT_BARCODE_FORMAT, 0);

            /*Callback cb = new Callback<SpoProduct>() {
                @Override
                public void success(SpoProduct p, Response response) {
                    String str = p.spop_status + " - " + p.spop_pr_sku;
                    //barcodeData.setText(strObj);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getApplicationContext(), "Error: " + retrofitError.toString(), Toast.LENGTH_LONG).show();
                    //barcodeData.setText(retrofitError.toString());
                }
            };
            client.getProductById(1, 1, cb);*/
/*
            Callback cb = new Callback<Spo>() {
                @Override
                public void success(Spo s, Response response) {
                    String str = s.spo_status + " - " + s.spo_date_ordered + "\n";
                    for (SpoProduct p : s.products) {
                        str += p.spop_status + " - " + p.spop_pr_sku + " - " + p.spop_expected_arrival + "\n";
                    }
                    //barcodeData.setText(strObj);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getApplicationContext(), "Error: " + retrofitError.toString(), Toast.LENGTH_LONG).show();
                    //barcodeData.setText(retrofitError.toString());
                }
            };
            client.getSpoById(1, cb);
*/

            Callback cb = new Callback<ArrayList<Spo>>() {
                @Override
                public void success(ArrayList<Spo> l, Response response) {
                    String str = "";

                    for (Spo s : l) {
                        str += s.spo_id + " - " + s.spo_status + " - " + s.spo_date_ordered + "\n";

                        //barcodeData.setText(strObj);
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getApplicationContext(), "Error: " + retrofitError.toString(), Toast.LENGTH_LONG).show();
                    //barcodeData.setText(retrofitError.toString());
                }
            };
            getClient().getSpoBySuIdAndShipmentCode(1, "803207203", cb);
            barcodeData.setText(ScanIntent.EXTRA_RESULT_BARCODE_DATA + ": " + data + "\r\n" + ScanIntent.EXTRA_RESULT_BARCODE_FORMAT + ": " + format);

        }
        else
            barcodeData.setText(R.string.scan_failed);
    }
}