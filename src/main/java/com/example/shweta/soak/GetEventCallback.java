package com.example.shweta.soak;

/**
 * Created by Shweta on 30-04-2015.
 */
interface GetEventCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(Event returnedEvent);
}
