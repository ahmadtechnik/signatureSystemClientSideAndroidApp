package com.example.signatureclientdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class wifiStatListener extends BroadcastReceiver {


    public static String get_connectionStatus() {
        return __CONNECTION_STATUS;
    }

    private static String __CONNECTION_STATUS;

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            __CONNECTION_STATUS = "CONNECTED";

        } else {
            __CONNECTION_STATUS = "DECONNECTED";

        }

    }

}
