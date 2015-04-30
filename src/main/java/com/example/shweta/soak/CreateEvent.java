package com.example.shweta.soak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CreateEvent extends ActionBarActivity implements View.OnClickListener {
    EditText etTitle, etAge, etDescription, etExtentOfReach, etDate, etTime, etCategory;
    Button bCreateEvent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("Create Event");

        setContentView(tv);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etAge = (EditText) findViewById(R.id.etAge);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etExtentOfReach = (EditText) findViewById(R.id.etExtentOfReach);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etCategory = (EditText) findViewById(R.id.etCategory);
        bCreateEvent = (Button) findViewById(R.id.bCreateEvent);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreateEvent:
                String title = etTitle.getText().toString();
                String age = etAge.getText().toString();
                String description = etDescription.getText().toString();
                String time = etTime.getText().toString();
                String date = etDate.getText().toString();
                String extentOfReach = etExtentOfReach.getText().toString();
                String category = etCategory.getText().toString();

                Event event = new Event(title, date, time, extentOfReach, category, description, age);
                //registerEvent(event);
                break;
        }
    }
}


