package com.a72h.a72h;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.R.attr.data;


class DownloadTask extends AsyncTask<String, Integer, String[]> {

    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String[] doInBackground(String... urls) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Internet State");
        String result[] = {""};
        try {
            try {
                if (isInternetAvailable()) {
                    URL url = new URL(urls[0]);
                    File file = new File(context.getCacheDir() + "/weather.csv");

                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();

                    byte[] buffer = new byte[1024];
                    int curLength = 0; // You can use current this to update progress bar
                    int newLength = 0;

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    while ((newLength = inputStream.read(buffer)) > 0) {
                        curLength += newLength;
                        byteArrayOutputStream.write(buffer, 0, newLength);
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(byteArrayOutputStream.toByteArray());
                    fos.close();
                    //Download has finished
                    alert.setMessage("Internet Found - Download Successful\n\nFile has been downloaded to " + context.getCacheDir() + "/weather.csv");
                    alert.show();
                } else {
                    alert.setMessage("No Internet - Download Failed\n\nFile is being retreived from " + context.getCacheDir() + "/weather.csv");
                    alert.show();
                }

                // From here on code is related to file reading
                String path = context.getCacheDir().getAbsolutePath() + "/weather.csv";
                File nfile = new File(path);
                if (nfile.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(nfile));
                    String line = "";
                    String[] result2 = {""};
                    try {
                        while ((line = br.readLine()) != null) {
                            result2 = line.split(";");
                        }
                        return result2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
