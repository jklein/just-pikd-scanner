package com.ellasmarket.emscanner.rest;

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
    private String authToken = null;
    private RestAdapter restAdapter = null;
    private WMS_API api;

    public APIClient() {
        buildApi();
    }

    public APIClient(String token) {
        authToken = token;
        buildApi();
    }

    public void buildApi() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(10, TimeUnit.SECONDS);    // socket timeout

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(client))
                .setRequestInterceptor(getRequestInterceptor())
                .build();
        api = restAdapter.create(WMS_API.class);
    }

    public WMS_API getApi() {
        return api;
    }

    public RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade r) {
                if (authToken != null) {
                    r.addHeader(AUTH_HEADER, authToken);
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
