package com.ellasmarket.emscanner;

import com.ellasmarket.emscanner.model.Spo;
import com.ellasmarket.emscanner.model.SpoProduct;
import com.google.gson.JsonElement;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Scott on 3/2/15.
 */


public class APIClient {
    private static final String API_URL = "http://192.168.3.14:3000";

    interface SpoTest {
        @GET("/spos/{id}")
        void getById(@Path("id") int id, Callback<JsonElement> cb);
    }

    interface WMS_API {
        @GET("/spos/{id}/products/{product_id}")
        void getProductById(@Path("id") int id, @Path("product_id") int product_id, Callback<SpoProduct> cb);

        @GET("/spos/{id}")
        void getSpoById(@Path("id") int id, Callback<Spo> cb);

        @GET("/spos")
        void getSpoBySuId(@Query("supplier_id") int supplier_id, Callback<ArrayList<Spo>> cb);
        @GET("/spos")
        void getSpoByShipmentCode(@Query("shipment_code") String shipment_code, Callback<ArrayList<Spo>> cb);
        @GET("/spos")
        void getSpoBySuIdAndShipmentCode(@Query("supplier_id") int supplier_id, @Query("shipment_code") String shipment_code, Callback<ArrayList<Spo>> cb);
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
