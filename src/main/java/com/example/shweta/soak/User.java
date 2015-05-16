package com.example.shweta.soak;

/**
 * Created by Shweta on 29-04-2015.
 */
public class User {

    String name, username, password,location,interest;
    String age;

    public User(String name, String age, String username, String password, String location,String interest) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.location = location;
        this.interest = interest;
    }

    public User(String username, String password) {
        this("", "", username, password,"","");
    }
}
