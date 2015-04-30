package com.example.shweta.soak;

/**
 * Created by Shweta on 29-04-2015.
 */
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tundealao on 29/03/15.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("name", user.name);
        userLocalDatabaseEditor.putString("username", user.username);
        userLocalDatabaseEditor.putString("password", user.password);
        userLocalDatabaseEditor.putInt("age", user.age);
        userLocalDatabaseEditor.putString("location", user.location);
        userLocalDatabaseEditor.putString("interest", user.interest);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String location = userLocalDatabase.getString("location", "");
        String interest = userLocalDatabase.getString("interest", "");
        int age = userLocalDatabase.getInt("age", -1);

        User user = new User(name, age, username, password,location,interest);
        return user;
    }
}