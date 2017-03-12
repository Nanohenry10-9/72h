package com.a72h.a72h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class powerOutage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_outage);
        WebView myWebView = (WebView) findViewById(R.id.WebView);
        myWebView.loadUrl("http://tiedostot.spek.fi/homeemergency/desktop/index.html?article=1&page=1");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
