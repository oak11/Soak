package com.example.shweta.soak;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shweta on 30-04-2015.
 */
public class EventLocalStore {

    public static final String SP_NAME = "eventDetails";

    SharedPreferences eventLocalDatabase;

    public EventLocalStore(Context context) {
        eventLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeEventData(Event event) {
        SharedPreferences.Editor eventLocalDatabaseEditor = eventLocalDatabase.edit();
        eventLocalDatabaseEditor.putString("title", event.title);
        eventLocalDatabaseEditor.putString("date", event.date);
        eventLocalDatabaseEditor.putString("time", event.time);
        eventLocalDatabaseEditor.putString("age", event.age);
        eventLocalDatabaseEditor.putString("extentOfReach", event.extentOfReach);
        eventLocalDatabaseEditor.putString("category", event.category);
        eventLocalDatabaseEditor.putString("description", event.description);
        eventLocalDatabaseEditor.commit();
    }

    public void setEventLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor eventLocalDatabaseEditor = eventLocalDatabase.edit();
        eventLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        eventLocalDatabaseEditor.commit();
    }

    public void clearEventData() {
        SharedPreferences.Editor eventLocalDatabaseEditor = eventLocalDatabase.edit();
        eventLocalDatabaseEditor.clear();
        eventLocalDatabaseEditor.commit();
    }

    public Event getLoggedInEvent() {
        if (eventLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String title = eventLocalDatabase.getString("title", "");
        String date = eventLocalDatabase.getString("date", "");
        String time = eventLocalDatabase.getString("time", "");
        String extentOfReach = eventLocalDatabase.getString("extentOfReach", "");
        String category = eventLocalDatabase.getString("category", "");
        String age = eventLocalDatabase.getString("age","");
        String description = eventLocalDatabase.getString("decription","");

        Event event  = new Event(title, date, time, extentOfReach, category,description, age);
        return event;
    }
}