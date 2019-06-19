package com.example.signatureclientdevice;

import android.content.Context;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.util.Scanner;

public class fileHandling {


    public final String TAG = "FileHandlingLog";
    private static Context context;

    // constractor to set context
    public fileHandling(Context context) {
        this.context = context;
    }

    //
    public boolean checkIfStaticFileExist() {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.static_);
            is.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //
    public boolean createNewStaticFile() {

        return false;
    }

    // return json as object
    public JSONObject JSONURLObject() {
        JSONObject jo = null;
        if (checkIfStaticFileExist()) {
            try {
                InputStream is = context.getResources().openRawResource(R.raw.static_);
                String s = new Scanner(is).useDelimiter("\\A").next();
                is.close();

                return new JSONObject(s);

            } catch (Exception e) { }
        } else {
            return null ;
        }
        return jo;
    }


    //
    public String JSONobjectToURL(){
        String url = "";
        JSONObject jo = JSONURLObject();
        if(jo != null){
            try {
                JSONObject serverDataObject = jo.getJSONObject("serverData");
                String protocol = serverDataObject.getString("PROTOCOL");
                String hostname = serverDataObject.getString("HOSTNAME");
                String port = serverDataObject.getString("PORT");
                String subdir = serverDataObject.getString("SUBDIR");
                return protocol + "://" + hostname + ":" + port + "/" + subdir;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return url ;
    }
}
