package com.ellasmarket.emscanner.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ellasmarket.emscanner.application.GlobalState;
import com.ellasmarket.emscanner.rest.APIClient;
import com.ellasmarket.emscanner.rest.WMS_API;

import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Created by Scott on 3/16/15.
 */
public class BaseActivity extends FragmentActivity {

    private WMS_API client;
    public GlobalState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new APIClient().getApi();
        gs = (GlobalState) getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.clearCroutonsForActivity(this);
        ButterKnife.reset(this);
    }

    public WMS_API getClient() {
        return client;
    }

    public void showError(String message) {
        Crouton.makeText(this, message, Style.ALERT)
                .show();
    }
}
