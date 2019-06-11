package com.example.signatureclientdevice;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    String TAG = "LOGGGG";

    private WebView myWebView;
    private WebSettings ws;
    private WebViewClient wc;
    private networking nw;

    private Fragment connectToWifiFragment;
    private Fragment pleaseEnableWifiFragment;
    private ConstraintLayout constraintLayout;

    private final String URL = "https://192.168.178.32:3000/client_side_home";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        /**
         * set the main layout as main home page to android
         * make app as kiosk
         * **/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // set activity as main activity
        setContentView(R.layout.activity_main);


    }

    // to reset app as kiosk mode on pause action
    @Override
    protected void onPause() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);

        super.onPause();
    }

    // to disable Key down
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("LOGGGG", String.valueOf(event.getKeyCode()));
        return false;
    }

    // to disable dispatch keys
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean result;

        switch (event.getKeyCode()) {

            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_POWER:
            case KeyEvent.KEYCODE_HOME:
                result = true;
                break;

            default:
                result = super.dispatchKeyEvent(event);
                break;
        }

        return result;
    }

    @Override
    protected void onStart() {
        /**
         * actions on steps
         * - check if wifi connected
         * - check if the static data are exist
         * - connect to data
         * **/
        nw = new networking(this);
        // if wifi is enabled
        if (nw.isWifiEnabled()) {

            // if wifi is connected
            if (nw.isWifiConnected()) {

            }
            // in case wifi was not connected
            else {
                nw.showPleaseConnectToWifiActivity();
            }
        }
        // in case wifi was not enabled
        else {
            nw.showPleaseEnableWifiLayout();
        }
        super.onStart();
    }

}


