package com.ellasmarket.emscanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.model.StationChangeRequest;
import com.ellasmarket.emscanner.model.StationChangeResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Scott on 4/3/15.
 */
public class TaskSelectionActivity extends BaseActivity {
    @InjectView(R.id.progress) ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_selection);
        ButterKnife.inject(this);
    }

    public void LaunchReceiving() {
        //TODO change to ReceivingActivity
        startActivity(new Intent(this, ReceivingActivity.class));
    }

    //when button clicked:
    //do http request to assign self to that module
    //open that module's activity'

    //when button is clicked -> get client, do API request to log in. start a spinner. if success, show success and then go to next activity.
    //if failure, show failure message and do not go to next activity.
    @OnClick(R.id.btn_receiving) void SelectReceiving() {
        StationChangeRequest req = new StationChangeRequest(getText(R.string.receiving).toString());
        Callback cb = new Callback<StationChangeResponse>() {
            @Override
            public void success(StationChangeResponse r, Response response) {
                progressBar.setVisibility(View.INVISIBLE);
                updateToken(r.token);
                LaunchReceiving();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("TaskSelection", retrofitError.getMessage());
                showError("ERROR: " + retrofitError.getKind().name() + " error");
            }
        };
        getClient().postStationChange(gs.getUserId(), req, cb);
        progressBar.setVisibility(View.VISIBLE);
    }

}
