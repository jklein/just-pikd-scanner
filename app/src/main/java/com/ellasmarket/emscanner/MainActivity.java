package com.ellasmarket.emscanner;

/*
 * Copyright G2G Market Inc, 2015
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ellasmarket.emscanner.model.Spo;
import com.ellasmarket.emscanner.model.SpoProduct;
import com.google.gson.JsonElement;
import com.honeywell.scanintent.ScanIntent;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

public class MainActivity extends Activity {
    TextView barcodeData = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        barcodeData = (TextView) findViewById(R.id.data);
    }

    /*static class Spo {
        int spo_id;
        String spo_status;
    }*/

    interface SpoTest {
        @GET("/spos/{id}")
        void getById(@Path("id") int id, Callback<JsonElement> cb);
    }

    public void onSendButtonClick(View v) {

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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == ScanIntent.SCAN_RESULT_SUCCESSED) {
            String data = intent
                    .getStringExtra(ScanIntent.EXTRA_RESULT_BARCODE_DATA);
            int format = intent.getIntExtra(
                    ScanIntent.EXTRA_RESULT_BARCODE_FORMAT, 0);
            APIClient.WMS_API client = new APIClient().getApi();

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

            Callback cb = new Callback<Spo>() {
                @Override
                public void success(Spo s, Response response) {
                    String str = s.spo_status + " - " + s.spo_su_id + "\n";
                    for (SpoProduct p : s.products) {
                        str += p.spop_status + " - " + p.spop_pr_sku + "\n";
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

            barcodeData.setText(ScanIntent.EXTRA_RESULT_BARCODE_DATA+ ": " + data + "\r\n" + ScanIntent.EXTRA_RESULT_BARCODE_FORMAT + ": " + format);

        }
        else
            barcodeData.setText(R.string.scan_failed);
    }
}