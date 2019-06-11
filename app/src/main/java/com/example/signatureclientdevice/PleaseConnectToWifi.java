package com.example.signatureclientdevice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PleaseConnectToWifi extends AppCompatActivity {

    networking nw;
    private Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        nw.showPleaseConnectToWifiActivity();
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
