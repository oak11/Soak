package com.example.shweta.soak;

/**
 * Created by Shweta on 30-04-2015.
 */
public class Event {
    String title, date, time,extentOfReach,category,description,age;


    public Event(String title, String date, String time, String extentOfReach, String category,String description,String age) {
        this.title = title;
        this.age = age;
        this.date = date;
        this.time = time;
        this.extentOfReach = extentOfReach;
        this.category = category;
        this.description=description;
    }
}
