package com.example.signatureclientdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class wifiStatListener  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("LOGGGG" , "FROM CLASS ");
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
            Log.i("LOGGGG", "Have Wifi Connection");
        else
            Log.i("LOGGGG", "Don't have Wifi Connection");

    }

}
