package com.ellasmarket.emscanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ellasmarket.emscanner.R;
import com.ellasmarket.emscanner.model.AuthRequest;
import com.ellasmarket.emscanner.model.AuthResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Scott on 3/23/15.
 */
public class LoginActivity extends BaseActivity {
    @InjectView(R.id.pin) EditText pin;
    @InjectView(R.id.progress) ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    public void next() {
        startActivity(new Intent(this, TaskSelectionActivity.class));
    }

    //when button is clicked -> get client, do API request to log in. start a spinner. if success, show success and then go to next activity.
    //if failure, show failure message and do not go to next activity.
    @OnClick(R.id.log_in_button) void submit() {
        AuthRequest req = new AuthRequest(pin.getText().toString());
        Callback cb = new Callback<AuthResponse>() {
            @Override
            public void success(AuthResponse r, Response response) {
                progressBar.setVisibility(View.INVISIBLE);
                updateToken(r.token);
                gs.setUserId(r.id);
                gs.setUsername(r.name);
                next();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("Login", retrofitError.getMessage());
                if(retrofitError.getKind() == RetrofitError.Kind.HTTP && retrofitError.getResponse().getStatus() == 404) {
                    showError("Invalid Login!");
                } else {
                    showError("Login Failed - " + retrofitError.getKind().name() + " error");
                }
            }
        };
        getClient().postAuth(req, cb);
        progressBar.setVisibility(View.VISIBLE);
    }

}
