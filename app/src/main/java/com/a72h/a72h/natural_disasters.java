package com.a72h.a72h;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class natural_disasters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natural_disasters);
    }

    public void downloadFile(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DownloadTask download = new DownloadTask(view.getContext());
        TextView tv = (TextView) findViewById(R.id.TV);
        String text[] = download.doInBackground("http://meteolux.lu/Opendata/data_alerts.csv");
        tv.setText(text.toString());

        TextView row1[] = {};
        row1[0] = (TextView) findViewById(R.id.col1);
        row1[1] = (TextView) findViewById(R.id.col2);
        row1[2] = (TextView) findViewById(R.id.col3);
        row1[3] = (TextView) findViewById(R.id.col4);
        row1[4] = (TextView) findViewById(R.id.col5);
        row1[5] = (TextView) findViewById(R.id.col6);
        row1[6] = (TextView) findViewById(R.id.col7);
        row1[7] = (TextView) findViewById(R.id.col8);
        row1[8] = (TextView) findViewById(R.id.col9);
        row1[9] = (TextView) findViewById(R.id.col10);
        try {
            for (int i = 0; i < 10; i++) {
                row1[i].setText(text[i]);
            }
        } catch (Exception e) {
            tv.setText("Error: " + e);
        }
        //col2row1.setText(text[1]);
    }

}
