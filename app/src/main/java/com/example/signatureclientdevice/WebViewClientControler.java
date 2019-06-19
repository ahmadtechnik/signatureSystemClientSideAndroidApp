package com.example.signatureclientdevice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientControler extends WebViewClient {

    public final String TAG = "WebViewClientControler";

    public Context context ;

    public WebViewClientControler(Context context) {
        this.context = context;
    }

    // handle ssh error
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Log.i(TAG, "onReceivedSslError ..." );
        handler.proceed(); // Ignore SSL certificate errors
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.i(TAG, "onPageStarted ...");
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i(TAG, "onPageFinished ...");
        super.onPageFinished(view, url);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        Log.i(TAG, "onPageCommitVisible ...");
        super.onPageCommitVisible(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        // show the fragment which is responsible on
        // enter the server data  to store the data into static file

        ((Activity) context).findViewById(R.id.getServerDataFragmentContainer).setVisibility(View.VISIBLE);
        Log.i(TAG, "onReceivedError ...");
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        Log.i(TAG, "onReceivedHttpError ..." );
        super.onReceivedHttpError(view, request, errorResponse);
    }


}
