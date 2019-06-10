package com.example.signatureclientdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class connectToWifi  extends AppCompatActivity   {

private EditText SSID_FIELD ;
private EditText PASSWORD ;
private Button SUBMITWIFIDATA ;
    String SSIDNAME;
    String PASSWIRDSt;
    private WifiConfiguration wc;
    WifiManager wifiManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_wifi);


        BroadcastReceiver BroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {

                ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMan.getActiveNetworkInfo();
                if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
                    Log.d("WifiReceiver", "Have Wifi Connection");
                else
                    Log.d("WifiReceiver", "Don't have Wifi Connection");
            }

        };

        BroadcastReceiver.onReceive(getApplicationContext(),this.getIntent());

    }

    /**
     * if device conneted to Wifi should this context be redirect
     * to main activity
     * **/
    public  void ifConnectedRetrunToMainActivity(Context context){
        networking nt = new networking(context);
        if(nt.isWifiConnected()){
            nt.showMainActivity();
        }

    }

    @Override
    protected void onStart() {

        ifConnectedRetrunToMainActivity(this);

        SSID_FIELD= (EditText) findViewById(R.id.SSID_FIELD);
        PASSWORD = (EditText) findViewById(R.id.PASSWORD);
        SUBMITWIFIDATA = (Button) findViewById(R.id.SUBMITWIFIDATA);

        SSIDNAME = SSID_FIELD.getText().toString();
        PASSWIRDSt = PASSWORD.getText().toString();


        SUBMITWIFIDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                networking nt = new networking(getApplicationContext());
                if (nt.isWifiConnected()){
                    nt.showMainActivity();
                }

                SSIDNAME = SSID_FIELD.getText().toString();
                PASSWIRDSt = PASSWORD.getText().toString();
                Log.i("LOGGGG" , SSIDNAME + " __ "  + " ___  " + PASSWIRDSt);
                wc = new WifiConfiguration();
                wc.SSID = String.format("\"%s\"", SSIDNAME);

                wc.preSharedKey = String.format("\"%s\"", PASSWIRDSt);

                wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);


                int netId = wifiManager.addNetwork(wc);

                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();



            }

        });

        super.onStart();
    }



}
