package com.ellasmarket.emscanner.rest;

import com.ellasmarket.emscanner.model.AuthRequest;
import com.ellasmarket.emscanner.model.AuthResponse;
import com.ellasmarket.emscanner.model.Spo;
import com.ellasmarket.emscanner.model.SpoProduct;
import com.ellasmarket.emscanner.model.StationChangeRequest;
import com.ellasmarket.emscanner.model.StationChangeResponse;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Scott on 3/16/15.
 */
public interface WMS_API {
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

    @POST("/associates/login")
    void postAuth(@Body AuthRequest user, Callback<AuthResponse> cb);

    @POST("/associates/{id}/station")
    void postStationChange(@Path("id") int id, @Body StationChangeRequest station, Callback<StationChangeResponse> cb);
}
