package com.example.shweta.soak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EventPage extends ActionBarActivity implements View.OnClickListener{


    EditText etTitleDisplay, etDateDisplay, etTimeDisplay,etDescriptionDisplay;
    Button bJoin;
    EventLocalStore eventLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        etTitleDisplay = (EditText) findViewById(R.id.etTitleDisplay);
        etDateDisplay = (EditText) findViewById(R.id.etDateDisplay);
        etTimeDisplay = (EditText) findViewById(R.id.etTimeDisplay);
        etDescriptionDisplay = (EditText) findViewById(R.id.etDescriptionDisplay);
        bJoin = (Button) findViewById(R.id.bJoin);

        bJoin.setOnClickListener(this);

         eventLocalStore = new EventLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                eventLocalStore.clearEventData();
                eventLocalStore.setEventLoggedIn(false);
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        }
    }

    private boolean authenticate() {
        if (eventLocalStore.getLoggedInEvent() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        Event event = eventLocalStore.getLoggedInEvent();
        etTitleDisplay.setText(event.title);
        etTimeDisplay.setText(event.time);
        etDateDisplay.setText(event.date + "");
        etDescriptionDisplay.setText(event.description + "");
    }
}