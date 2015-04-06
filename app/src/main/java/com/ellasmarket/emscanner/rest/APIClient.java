package com.ellasmarket.emscanner.rest;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Scott on 3/2/15.
 */


public class APIClient {
    private static final String API_URL = "http://192.168.3.90:3000";
    public static final String AUTH_HEADER = "X-Auth-Token";
    private RestAdapter restAdapter = null;
    private WMS_API api;

    public APIClient(String token) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(10, TimeUnit.SECONDS);    // socket timeout

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(client))
                .setRequestInterceptor(getRequestInterceptor(token))
                .build();

        if (token != null) {
            Log.d("APICLIENT", "should have just build a request interceptor with token " + token);
        }
        api = restAdapter.create(WMS_API.class);
    }

    public WMS_API getApi() {
        return api;
    }

    public RequestInterceptor getRequestInterceptor(final String token) {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade r) {
                Log.d("APICLIENT", "got here - getRequestInterceptor");
                if (token != null) {
                    Log.d("APICLIENT", "Also got in here, setting " + AUTH_HEADER + " to " + token);
                    r.addHeader(AUTH_HEADER, token);
                } else {
                    Log.d("APICLIENT", "Apparently token is null");
                }
            }
        };
    }

    /*public void GetSpoProduct(int id, int product_id, Callback<SpoProduct> cb) {
        WMS_API spotest = restAdapter.create(WMS_API.class);
        List<Contributor> contributors = github.contributors("square", "retrofit");
    }*/

    /*Callback cb = new Callback<JsonElement>() {
        @Override
        public void success(JsonElement element, Response response) {
            JsonObject jsonObj = element.getAsJsonObject();
            String strObj = element.toString();
            //barcodeData.setText(strObj);
            Toast.makeText(getApplicationContext(), strObj, Toast.LENGTH_LONG).show();
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Toast.makeText(getApplicationContext(), "Error: " + retrofitError.toString(), Toast.LENGTH_LONG).show();
            //barcodeData.setText(retrofitError.toString());
        }
    };
    */
}
