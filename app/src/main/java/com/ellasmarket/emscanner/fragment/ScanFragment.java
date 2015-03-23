package com.ellasmarket.emscanner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ellasmarket.emscanner.R;
import com.honeywell.scanintent.ScanIntent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Scott on 3/23/15.
 *
 * Generic handler for scan events to be included by any activity
 */
public class ScanFragment extends Fragment {
    //private static final String LOG_TAG = ScanFragment.class.getSimpleName();

    @InjectView(R.id.input) EditText input;
    @InjectView(R.id.result) TextView result;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.submit) void submit() {
        setResult();
    }

    public void setResult() {
        result.setText(input.getText());
    }

    public void startScan() {
        Intent intentScan = new Intent(ScanIntent.SCAN_ACTION);
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);
        intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentScan.putExtra("scan_mode", ScanIntent.SCAN_MODE_RESULT_AS_URI);
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

            result.setText(ScanIntent.EXTRA_RESULT_BARCODE_DATA + ": " + data);

        }
        else
            result.setText(R.string.scan_failed);
    }
}


//junky callback stuff to copy paste later
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
/*
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
            */