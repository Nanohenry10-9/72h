package com.a72h.a72h;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }
            int fileLength = connection.getContentLength();
            if (fileLength == -1) {
                return "File length not reported!";
            }
            is = connection.getInputStream();
            //Reader redr = ;
            //BufferedReader bufRead = new BufferedReader(redr);
            File nfile = new File(context.getCacheDir().getAbsolutePath() + "/weather.csv");
            BufferedReader bufRead = new BufferedReader(new FileReader(nfile));
            byte data[] = new byte[4096];
            String line = "";
            String datas[] = {""};
            while ((line = bufRead.readLine()) != null) {
                // allow canceling with back button
                if (isCancelled()) {
                    is.close();
                    return "Download cancelled!";
                }
                // publishing the progress....
                datas = line.split(";");
                for (String item : datas) {
                    result += item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Exception: " + e;
        }
        return result;
    }

    public String byteArrayToString(char[] in) {
        char out[] = new char[in.length * 2];
        for (int i = 0; i < in.length; i++) {
            out[i * 2] = "0123456789ABCDEF".charAt((in[i] >> 4) & 15);
            out[i * 2 + 1] = "0123456789ABCDEF".charAt(in[i] & 15);
        }
        return new String(out);
    }
}
