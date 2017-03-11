package com.a72h.a72h;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... urls) {
        InputStream is = null;
        String result = "";
        String url = urls[0];

        return result;
    }
}
