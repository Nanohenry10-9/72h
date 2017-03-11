package com.a72h.a72h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void naturalDisasters(View view) {
        Intent intent = new Intent(this, natural_disasters.class);
        startActivity(intent);

    }

    public void checklist(View view) {
        Intent intent = new Intent(this, checklist.class);
        startActivity(intent);
    }

    public void meetingPoints(View view) {
        //Intent intent = new Intent(this, checklist.class);
        //startActivity(intent);
    }

    public void maps(View view) {
        //Intent intent = new Intent(this, checklist.class);
        //startActivity(intent);
    }

    public void powerOutage(View view) {
        //Intent intent = new Intent(this, checklist.class);
        //startActivity(intent);
    }

    public void firstAid(View view) {
        //Intent intent = new Intent(this, checklist.class);
        //startActivity(intent);
    }

}
