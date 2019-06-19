package com.example.signatureclientdevice;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivityLog";

    private WebView myWebView;
    private WebSettings ws;
    private WebViewClient wc;
    private networking nw;

    private Fragment connectToWifiFragment;
    private Fragment pleaseEnableWifiFragment;
    private ConstraintLayout constraintLayout;

    private ConstraintLayout getServerDataFragmentContainer;

    SharedPreferences sp ;

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



        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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
            // try to get the server data
            // from json static file
            if (nw.isWifiConnected()) {
                myWebView = (WebView) findViewById(R.id.mainWebView);
                myWebView.setWebViewClient(new WebViewClientControler(this));
                ws = myWebView.getSettings();
                ws.setJavaScriptEnabled(true);

                /**
                 * check if data of the server are stored in shared prefs.
                 * **/
                sp = getApplicationContext().getSharedPreferences("ServerData" , MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();

                String serverURL = sp.getString("serverURL" , "");
                Log.i(TAG ,"GETED : " +  serverURL );
                if(serverURL != ""){
                    Log.i(TAG ,"STORED...." );
                    myWebView.loadUrl(serverURL);
                }else{
                    myWebView.loadUrl("localhost:3000");
                    Log.i(TAG ,"NOT STORED YET...." );
                }

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

    @Override
    protected void onStop() {



        Toast.makeText(getApplicationContext() , "isWifiEnabled True" , Toast.LENGTH_LONG).show();
        Log.i(TAG, "From on activity stop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "From on main activity resume..");



        super.onResume();
    }
}


