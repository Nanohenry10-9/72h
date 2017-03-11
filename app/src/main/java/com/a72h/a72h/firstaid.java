package com.a72h.a72h;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kalyparker on 3/11/17.
 */

public class firstaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

        Button clickButton = (Button) findViewById(R.id.button_load);
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                DownloadTask temp = new DownloadTask(v.getContext());
                String file = temp.doInBackground("http://www.pharmacie.lu/flux_rss.xml");
                TextView text = (TextView) findViewById(R.id.textV);
                text.setText(file);
            }
        });

    }


}
