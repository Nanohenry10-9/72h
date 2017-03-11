package com.a72h.a72h;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by irneh on 23/02/2017.
 */

public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private PowerManager.WakeLock mWakeLock;

    public DownloadTask(Context context) {
        this.context = context;
    }

    private static final int BUFFER_SIZE = 4096;

    @Override
    protected String doInBackground(String... sUrl) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Data read:");

        try {
            URL url = new URL(sUrl[0]);
            File file = new File(context.getCacheDir()+"/tmp.csv");

            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int curLength = 0; // You can use current this to update progress bar
            int newLength = 0;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while((newLength = inputStream.read(buffer))>0)
            {
                curLength += newLength;
                byteArrayOutputStream.write(buffer, 0, newLength);
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArrayOutputStream.toByteArray());
            fos.close(); //Fished with downloading and storing file


            //Reading file content
            String path = context.getCacheDir().getAbsolutePath() + "/tmp.csv";
            File nfile = new File(path);
            if(nfile.exists())
            {
                //Reading file is exist
                BufferedReader br = new BufferedReader(new FileReader(nfile));
                String line = "";
                String cvsSplitBy = ";";
                String result = "";
                try {
                    while ((line = br.readLine()) != null) {
                        // use comma as separator
                        String[] data = line.split(cvsSplitBy);
                        for (String item : data) {
                            result += item;
                            System.out.println(item);
                        }
                    }
                    return result;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    /*if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }*/
                }
            }
            else {
                alert.setMessage("Couldn't find file, please download!");
                alert.show();
                return null;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
            //return null;
        }
        return null;
    }

}
