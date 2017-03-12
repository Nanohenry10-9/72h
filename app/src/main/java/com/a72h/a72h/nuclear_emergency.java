package com.a72h.a72h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebView;


/**
 * Created by kalyparker on 3/12/17.
 * feed comming from this repository, but actually outdated !
 * www.data.gouv.fr/fr/datasets/avis-d-incidents-dans-les-installations-nucleaires/
 */

public class nuclear_emergency extends AppCompatActivity {

    // All static variables
    static final String URL = "http://www.data.gouv.fr/fr/datasets/r/a2089d28-5feb-4021-96af-1ae5418ae631";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuclear);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadUrl("https://www.asn.fr/recherche/resultat/(SearchText)/cattenom/(SearchSite)/35330%2C139%2C94669%2C120925/(Rubrique)/Contr%C3%B4ler%23%2331");
    }


}
