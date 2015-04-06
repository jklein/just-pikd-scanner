package com.ellasmarket.emscanner.application;

import android.app.Application;

import com.ellasmarket.emscanner.bluetooth.BluetoothService;

/**
 * Created by Scott on 3/16/15.
 *
 * This class holds global state / session information.
 * For security reasons, the session is lost when the app is stopped so the user would need to log in again.
 */
public class GlobalState extends Application {
    private int userId;
    private String username;
    private String token;
    private BluetoothService mBluetoothService;

    @Override
    public void onCreate() {
        super.onCreate();
        userId = 0;
        username = "";
        token = "";
        mBluetoothService = new BluetoothService();
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BluetoothService getBluetoothService() {
        //initialize it here if it is null
        return mBluetoothService;
    }
}
