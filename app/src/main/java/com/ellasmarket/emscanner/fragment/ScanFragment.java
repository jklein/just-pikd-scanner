package com.ellasmarket.emscanner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.model.Spo;
import com.honeywell.scanintent.ScanIntent;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Scott on 3/23/15.
 */
public class ScanFragment extends Fragment {
    private static final String LOG_TAG = ScanFragment.class.getSimpleName();

    @InjectView(R.id.input) EditText input;
    @InjectView(R.id.send_intent) Button send_intent;
    @InjectView(R.id.result) TextView result;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        ButterKnife.inject(this, view);
        send_intent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult();
            }
        });
        // TODO Use "injected" views...
        return view;
    }

    public void setResult() {
        result.setText(input.getText());
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

    public void setHint(String hint) {
        input.setHint(hint);
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
                        Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getActivity(), "Error: " + retrofitError.toString(), Toast.LENGTH_LONG).show();
                    //barcodeData.setText(retrofitError.toString());
                }
            };
            //getActivity().getClient().getSpoBySuIdAndShipmentCode(1, "803207203", cb);
            result.setText(ScanIntent.EXTRA_RESULT_BARCODE_DATA + ": " + data + "\r\n" + ScanIntent.EXTRA_RESULT_BARCODE_FORMAT + ": " + format);

        }
        else
            result.setText(R.string.scan_failed);
    }
}
