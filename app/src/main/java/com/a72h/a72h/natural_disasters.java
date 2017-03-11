package com.a72h.a72h;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class natural_disasters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natural_disasters);
    }

    public void downloadFile(View view) {
        DownloadTask download = new DownloadTask(view.getContext());
        TextView tv = (TextView) findViewById(R.id.TV);
        String text = download.doInBackground("http://meteolux.lu/Opendata/data_alerts.csv");
        tv.setText(text);
    }

}
