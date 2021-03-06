package com.getbeamapp.transit;

import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.getbeamapp.transit.android.AndroidTransitContext;
import com.getbeamapp.transit.android.prompt.TransitChromeClient;
import com.getbeamapp.transit.android.prompt.TransitPromptAdapter;

public class MainActivity extends Activity {

    public AndroidTransitContext transit;
    public WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        this.transit = TransitPromptAdapter.createContext(webView, new TransitChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("Console", consoleMessage.message());
                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient());
        
        transit.getAdapter().initialize();

        webView.loadData(
                "<html><head><title>Transit</title></head><body>Hello World!</body></html>",
                "text/html", null);

        setContentView(webView);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Log.i("DEBUG", transit.toString());
                return;
            }
        });
    }

}
