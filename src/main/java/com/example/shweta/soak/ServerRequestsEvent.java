package com.example.shweta.soak;

/**
 * Created by Shweta on 30-04-2015.
 */
import  android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;


public class ServerRequestsEvent {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://sql1.000webhost.com/phpMyAdmin/main.php?token=b46141fb63a41cdfd8dc366cf0ccdadf";

    public ServerRequestsEvent(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }

    public void storeEventDataInBackground(Event event,
                                          GetEventCallback eventCallBack) {
        progressDialog.show();
        new StoreEventDataAsyncTask(event, eventCallBack).execute();
    }

    public void fetchEventDataAsyncTask(Event event, GetEventCallback eventCallBack) {
        progressDialog.show();
        new fetchEventDataAsyncTask(event, eventCallBack).execute();
    }

    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreEventDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Event event;
        GetEventCallback eventCallBack;

        public StoreEventDataAsyncTask(Event event, GetEventCallback eventCallBack) {
            this.event = event;
            this.eventCallBack = eventCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("title", event.title));
            dataToSend.add(new BasicNameValuePair("date", event.date));
            dataToSend.add(new BasicNameValuePair("time", event.time));
            dataToSend.add(new BasicNameValuePair("age", event.age));
            dataToSend.add(new BasicNameValuePair("extentOfReach", event.extentOfReach));
            dataToSend.add(new BasicNameValuePair("category", event.category));
            dataToSend.add(new BasicNameValuePair("description", event.description));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "CreateEvent.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            eventCallBack.done(null);
        }

    }

    public class fetchEventDataAsyncTask extends AsyncTask<Void, Void, Event> {
        Event event;
        GetEventCallback eventCallBack;

        public fetchEventDataAsyncTask(Event event, GetEventCallback eventCallBack) {
            this.event = event;
            this.eventCallBack = eventCallBack;
        }

        @Override
        protected Event doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("title", event.title));
            dataToSend.add(new BasicNameValuePair("date", event.date));
            dataToSend.add(new BasicNameValuePair("time", event.time));
            dataToSend.add(new BasicNameValuePair("age", event.age));
            dataToSend.add(new BasicNameValuePair("extentOfReach", event.extentOfReach));
            dataToSend.add(new BasicNameValuePair("category", event.category));
            dataToSend.add(new BasicNameValuePair("description", event.description));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "FetchEventData.php");

            Event returnedEvent = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() != 0){
                    Log.v("happened", "2");
                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");
                    returnedEvent = new Event(event.title, event.date, event.time, event.extentOfReach, event.category,event.description,event.age);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedEvent;
        }

        @Override
        protected void onPostExecute(Event returnedEvent) {
            super.onPostExecute(returnedEvent);
            progressDialog.dismiss();
            eventCallBack.done(returnedEvent);
        }
    }
}