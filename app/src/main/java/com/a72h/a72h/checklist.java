package com.a72h.a72h;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
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
        Intent calendarIntent = new Intent(Intent.ACTION_EDIT);
        calendarIntent.setType("vnd.android.cursor.item/event");
        calendarIntent.putExtra("description", "72h: Check expiration date of [Insert you item here]");
        calendarIntent.putExtra("title", "[Enter title here]");
        calendarIntent.putExtra("allDay", true);
        startActivity(calendarIntent);
        EditText ed1 = new EditText(view.getContext());
        EditText ed2 = new EditText(view.getContext());
        EditText ed3 = new EditText(view.getContext());
        ed1.setHint("Item Name");
        ed2.setHint("Amount");
        ed3.setHint("Exp. Date");
        CheckBox cb = new CheckBox(view.getContext());
        TableLayout ll = (TableLayout) findViewById(R.id.tableView);
        TableRow row = new TableRow(view.getContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
        row.addView(ed1);
        row.addView(ed2);
        row.addView(ed3);
        row.addView(cb);
        row.setLayoutParams(lp);
        ll.addView(row);
    }
}
