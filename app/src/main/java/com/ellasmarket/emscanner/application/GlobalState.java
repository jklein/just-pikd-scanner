package com.ellasmarket.emscanner.application;

import android.app.Application;

import com.ellasmarket.emscanner.bluetooth.BluetoothChatService;

/**
 * Created by Scott on 3/16/15.
 *
 * This class holds global state / session information.
 * For security reasons, the session is lost when the app is stopped so the user would need to log in again.
 */
public class GlobalState extends Application {
    private static String username;
    private static String pin;
    private static BluetoothChatService mChatService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        username="";
        pin="";
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GlobalState.username = username;
    }

    public static String getPIN() {
        return pin;
    }

    public static void setPIN(String pin) {
        GlobalState.pin = pin;
    }

    //public static BluetoothChatService getBluetoothChatService() {
        //initialize it here if it is null
        //return mChatService;
    //}

}
