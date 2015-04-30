package com.example.shweta.soak;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class Wall extends ActionBarActivity implements View.OnClickListener {

Button bCreateEvent,bLogout,bResponse,bGoing,bUndecided;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wall);
        bCreateEvent = (Button) findViewById(R.id.bCreateEvent);
        bResponse = (Button) findViewById(R.id.bResponse);
        bGoing = (Button) findViewById(R.id.bGoing);
        bUndecided = (Button) findViewById(R.id.bUndecided);
        bLogout = (Button) findViewById(R.id.bLogout);

        bCreateEvent.setOnClickListener(this);
        bResponse.setOnClickListener(this);
        bGoing.setOnClickListener(this);
        bUndecided.setOnClickListener(this);
        bLogout.setOnClickListener(this);
           }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bGoing:
                Intent intent1 = new Intent(Wall.this, Going.class);
                startActivity(intent1);

                break;

            case R.id.bCreateEvent:
                Intent intent2 = new Intent(Wall.this, CreateEvent.class);
                startActivity(intent2);

                break;

            case R.id.bUndecided:
                Intent intent3 = new Intent(Wall.this, Undecided.class);
                startActivity(intent3);

                break;
            case R.id.bResponse:
                Intent intent4 = new Intent(Wall.this, Response.class);
                startActivity(intent4);

                break;

            case R.id.bLogout:
                Intent intent = new Intent(Wall.this, Login.class);
                startActivity(intent);

                break;


        }

    }
}


