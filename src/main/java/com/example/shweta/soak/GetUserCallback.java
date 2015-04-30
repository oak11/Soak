package com.example.shweta.soak;

/**
 * Created by Shweta on 29-04-2015.
 */
interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(User returnedUser);
}
