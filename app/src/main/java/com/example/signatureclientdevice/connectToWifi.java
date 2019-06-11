package com.example.signatureclientdevice;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class connectToWifi extends AppCompatActivity {


    private EditText SSID_FIELD;
    private EditText PASSWORD;
    private Button SUBMITWIFIDATA;
    private WifiConfiguration wc;
    private TextView topMsg;

    WifiManager wifiManager;
    String SSIDNAME;
    String PASSWIRDSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_wifi);

    }

    /**
     * if device conneted to Wifi should this context be redirect
     * to main activity
     **/
    public void ifConnectedRetrunToMainActivity(Context context) {
        networking nt = new networking(context);
        if (nt.isWifiConnected()) {
            nt.showMainActivity();
        }

    }

    @Override
    protected void onStart() {
        topMsg = (TextView) findViewById(R.id.wifiStatus);

        networking nt = new networking(getApplicationContext());
        if (nt.isWifiConnected()) {
            nt.showMainActivity();
        } else {
            // to check if wifi was enabled or not
            if (!nt.isWifiEnabled()) {
                topMsg.setText("Please Enable wifi connection..");
                topMsg.setTextColor(Color.RED);
            } else {
                topMsg.setText("Please connect to the network that the server is connected to.");
                topMsg.setTextSize(20);
                topMsg.setTextColor(Color.BLACK);
            }
        }


        SSID_FIELD = findViewById(R.id.SSID_FIELD);
        PASSWORD = findViewById(R.id.PASSWORD);
        SUBMITWIFIDATA = findViewById(R.id.SUBMITWIFIDATA);

        SSIDNAME = SSID_FIELD.getText().toString();
        PASSWIRDSt = PASSWORD.getText().toString();


        SUBMITWIFIDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SSIDNAME = SSID_FIELD.getText().toString();
                PASSWIRDSt = PASSWORD.getText().toString();
                Log.i("LOGGGG", SSIDNAME + " __ " + " ___  " + PASSWIRDSt);
                wc = new WifiConfiguration();
                wc.SSID = String.format("\"%s\"", SSIDNAME);

                wc.preSharedKey = String.format("\"%s\"", PASSWIRDSt);

                wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);


                int netId = wifiManager.addNetwork(wc);

                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();


            }
        });


        super.onStart();
    }


}
