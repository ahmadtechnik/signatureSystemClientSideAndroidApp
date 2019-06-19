package com.example.signatureclientdevice;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PleaseEnableWifi extends AppCompatActivity {
    private Button retryBtn ;
    private networking nw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * set the main layout as main home page to android
         * make app as kiosk
         * **/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_please_enable_wifi);

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
        retryBtn = (Button) findViewById(R.id.retryBtn) ;
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nw = new networking(getApplicationContext());
                // if wifi is enabled
                if (nw.isWifiEnabled()) {
                    // if wifi is connected
                    if (nw.isWifiConnected()) {
                        nw.showMainActivity();
                    }
                    // in case wifi was not connected
                    else {
                        nw.showPleaseConnectToWifiActivity();
                    }
                }
                // in case wifi was not enabled
                else {

                }
            }
        });
        super.onStart();
    }
}
