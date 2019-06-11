package com.example.signatureclientdevice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class networking {

    private NetworkInfo ni;
    private Context mc;
    private WifiManager wm;


    public networking(Context mc) {
        this.mc = mc;
    }

    public boolean isWifiConnected() {
        ConnectivityManager com = (ConnectivityManager) mc.getSystemService(mc.CONNECTIVITY_SERVICE);
        ni = com.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (ni.isConnected()) {
            /** in case was not connected i have to show the wifi data getter **/

            Log.i("LOGGGG", "Connected....");
            return true;
        }
        /****/

        return false;
    }

    public void ShowConnectActivity() {
        Intent intent = new Intent(mc, connectToWifi.class);
        mc.startActivity(intent);
    }

    public void showMainActivity() {
        Intent intent = new Intent(mc, MainActivity.class);
        mc.startActivity(intent);
    }

    public boolean isWifiEnabled() {
        wm = (WifiManager) mc.getSystemService(mc.WIFI_SERVICE);
        if (wm.isWifiEnabled()) {
            return true;
        }
        return false;
    }

    public void showPleaseEnableWifiLayout() {
        Intent intent = new Intent(mc, PleaseEnableWifi.class);
        mc.startActivity(intent);
    }

    public void showPleaseConnectToWifiActivity() {
        Intent intent = new Intent(mc, PleaseConnectToWifi.class);
        mc.startActivity(intent);
    }
}
