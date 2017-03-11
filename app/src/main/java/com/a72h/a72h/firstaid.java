package com.a72h.a72h;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.os.StrictMode;

import android.os.AsyncTask;
import com.a72h.a72h.firstaid_parser.Entry;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Created by kalyparker on 3/11/17.
 */

public class firstaid extends AppCompatActivity {


    // All static variables
    static final String URL = "http://www.pharmacie.lu/flux_rss.xml";

    // XML node keys
    static final String KEY_NAME = "name";
    // static final String KEY_COST = "type"; // Hospital or Pharmacy
    static final String KEY_DESC = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

    }

    @Override
    public void onStart() {
        super.onStart();
        loadPage();
    }

    private void loadPage() {
        new DownloadXmlTask().execute(URL);
    }

    private class DownloadXmlTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            } catch (XmlPullParserException e) {
                return getResources().getString(R.string.xml_error);
            }
        }
        @Override
        protected void onPostExecute(String result) {
            setContentView(R.layout.activity_firstaid);
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadData(result, "text/html", null);
        }
    }


    private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        firstaid_parser feedXmlParser = new firstaid_parser();
        List<Entry> entries = null;
        String url = null;
        StringBuilder htmlString = new StringBuilder();
        try {
            stream = downloadUrl(urlString);
            entries = feedXmlParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        for (Entry entry : entries) {
            htmlString.append("<h3>");
            htmlString.append(entry.title);
            htmlString.append("</h3>");
            htmlString.append(entry.description);
        }
        return htmlString.toString();
    }
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

}
