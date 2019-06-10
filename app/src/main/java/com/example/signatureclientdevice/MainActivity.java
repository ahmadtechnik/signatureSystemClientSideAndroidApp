package com.example.signatureclientdevice;

import android.annotation.SuppressLint;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    String TAG = "LOGGGG";

private  WebView myWebView ;
private WebSettings ws;
private WebViewClient wc;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /**
         * set the main layout as main home page to android
         * **/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);






    }

    //
    @Override
    protected void onPause() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        super.onPause();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("LOGGGG" , String.valueOf(event.getKeyCode()));
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean result;

        switch( event.getKeyCode() ) {

            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case  KeyEvent.KEYCODE_POWER:
            case KeyEvent.KEYCODE_HOME:
                result = true;
                break;

            default:
                result= super.dispatchKeyEvent(event);
                break;
        }

        return result;
    }

    @Override
    protected void onStart() {
        // check if device connected to wifi
        networking nt = new networking(this);
        if(!nt.isWifiConnected()){
            nt.ShowConnectActivity();
        }else{
            WebView myWebView ;
            WebSettings ws;
            WebViewClient wc;
            // to reloade the URL to webview
            myWebView = findViewById(R.id.mainWebView);
            ws = myWebView.getSettings();
            wc = new WebViewClient();
            ws.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new sslHandler());
            myWebView.loadUrl("https://192.168.178.32:3000/client_side_home");

        }

        super.onStart();
    }
}


