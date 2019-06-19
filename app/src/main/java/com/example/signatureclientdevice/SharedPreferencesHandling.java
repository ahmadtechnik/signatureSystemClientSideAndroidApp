package com.example.signatureclientdevice;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesHandling {

    // to check if the url was stored in the shared prefrences
    public boolean checkIfSharedURLstored(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        if(sp.contains("CLIENTURL")){
            return true;
        }
        return false;
    }
}
