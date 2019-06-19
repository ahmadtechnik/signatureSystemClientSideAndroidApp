package com.example.signatureclientdevice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PleaseConnectToWifi extends AppCompatActivity {

    networking nw;
    private Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * set the main layout as main home page to android
         * make app as kiosk
         * **/

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_please_connect_to_wifi);
    }

    @Override
    protected void onStart() {
        retry = (Button) findViewById(R.id.retryBtn);
        retry.setOnClickListener(new View.OnClickListener() {
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

                    }
                }
                // in case wifi was not enabled
                else {
                    nw.showPleaseEnableWifiLayout();
                }

            }
        });
        super.onStart();
    }
}
