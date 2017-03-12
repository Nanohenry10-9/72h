package com.a72h.a72h;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.webkit.WebView;

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

        htmlString.append("<h2>Pharmacy on Call</h2>");
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
