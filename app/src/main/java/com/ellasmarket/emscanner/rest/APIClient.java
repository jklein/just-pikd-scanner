package com.ellasmarket.emscanner.rest;

import com.google.gson.JsonElement;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Scott on 3/2/15.
 */


public class APIClient {
    private static final String API_URL = "http://192.168.3.14:3000";

    interface SpoTest {
        @GET("/spos/{id}")
        void getById(@Path("id") int id, Callback<JsonElement> cb);
    }

    private RestAdapter restAdapter;
    private WMS_API api;

    public APIClient() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        api = restAdapter.create(WMS_API.class);
    }

    public WMS_API getApi() {
        return api;
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
