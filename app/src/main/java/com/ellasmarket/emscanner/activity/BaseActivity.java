package com.ellasmarket.emscanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.ellasmarket.emscanner.application.GlobalState;
import com.ellasmarket.emscanner.fragment.ScanFragment;
import com.ellasmarket.emscanner.rest.APIClient;
import com.ellasmarket.emscanner.rest.WMS_API;
import com.honeywell.scanintent.ScanIntent;

import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Created by Scott on 3/16/15.
 */
public class BaseActivity extends FragmentActivity {

    private WMS_API client;
    public GlobalState gs;

    //Many activities contain scan fragments which need to listen for a scan key event, so putting this here
    //and allowing child activities to set the fragment in onCreate helps DRY the onKeyDown method
    public ScanFragment scanFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new APIClient().getApi();
        gs = (GlobalState) getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
        ButterKnife.reset(this);
    }

    public WMS_API getClient() {
        return client;
    }

    public void showError(String message) {
        Crouton.makeText(this, message, Style.ALERT)
                .show();
    }

    public void showSuccess(String message) {
        Crouton.makeText(this, message, Style.CONFIRM)
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //KEYCODE_UNKNOWN is the value that the hardware scan button on honeywell
        //devices ends up sending.
        if (keyCode == KeyEvent.KEYCODE_UNKNOWN && scanFragment != null) {
            scanFragment.startScan();
            //true to indicate we handled the key event
            return true;
        }
        //otherwise call super class's method to handle other buttons
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == ScanIntent.SCAN_RESULT_SUCCESSED) {
            showSuccess("Scan Succeeded");
        }
        else
            showError("Scan Failed, Try Again");
        super.onActivityResult(requestCode, resultCode, intent);
    }
}
