package com.a72h.a72h;

import android.content.Context;
import android.os.AsyncTask;


class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... url) {
        String result = url[0];
        return result;
    }
}
