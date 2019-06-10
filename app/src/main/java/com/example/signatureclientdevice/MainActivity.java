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


public class MainActivity extends AppCompatActivity {
private  WebView myWebView ;
private WebSettings ws;
private WebViewClient wc;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        myWebView = findViewById(R.id.mainWebView);
        ws = myWebView.getSettings();

        ws.setJavaScriptEnabled(true);

        wc = new WebViewClient();

        myWebView.setWebViewClient(new sslHandler());

        if(myWebView != null )
        myWebView.loadUrl("https://192.168.178.32:3000/client_side_home");


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
}
