package com.a72h.a72h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class checklist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
    }

    public void addItem(View view) {
        EditText et = (EditText) findViewById(R.id.add1);
        TextView tv = new TextView(view.getContext());
        tv.setText(et.getText().toString());
        TableLayout ll = (TableLayout) findViewById(R.id.tableView);
        TableRow row = new TableRow(view.getContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        row.addView(tv);
        ll.addView(row);
    }
}
