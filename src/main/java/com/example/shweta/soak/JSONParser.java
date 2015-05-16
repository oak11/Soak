package com.example.shweta.soak;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Shweta on 09-05-2015.
 */
/*
import org.apache.http.HttpEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONParser {
    static InputStream is = null;
    static JSONObject jsonObj ;
    static String json = "";
     // default no argument constructor for jsonpaser
      JSONParser() {
        }
 public JSONObject getJSONFromUrl(final String url) {
     // Making HTTP request
     try {
         DefaultHttpClient httpClient = new DefaultHttpClient(); HttpPost httpPost = new HttpPost(url);
     // Executing POST request & storing the response from server locally.
     HttpResponse httpResponse = httpClient.execute(httpPost);
         HttpEntity httpEntity = httpResponse.getEntity();
         is = httpEntity.getContent();
     }
     catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     } catch (ClientProtocolException e) { e.printStackTrace(); }
     catch (IOException e) { e.printStackTrace(); }
     try {
     // Create a BufferedReader
     BufferedReader reader = new BufferedReader(new InputStreamReader(is ));
     // Declaring string builder
     StringBuilder str = new StringBuilder();
     // string to store the JSON object.
     String strLine = null;
     // Building while we have string !equal null.
     while ((strLine = reader.readLine()) != null) { str.append(strLine + "\n"); }
     // Close inputstream.
     is.close();
     // string builder data conversion to string.
     json = str.toString();
     } catch (Exception e) {
         Log.e("Error", " something wrong with converting result " + e.toString());
     }
     // Try block used for pasrseing String to a json object
     try { jsonObj = new JSONObject(json); }
     catch (JSONException e)
     { Log.e("json Parsering", "" + e.toString()); }
     // Returning json Object.
     return jsonObj; }

    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
     // Make HTTP request
     try {
     // checking request method
     if(method == "POST"){
     // now defaultHttpClient object
     DefaultHttpClient httpClient = new DefaultHttpClient();
     HttpPost httpPost = new HttpPost(url);
     httpPost.setEntity(new UrlEncodedFormEntity(params));
         HttpResponse httpResponse = httpClient.execute(httpPost);
         HttpEntity httpEntity = httpResponse.getEntity();
         is = httpEntity.getContent();
     }
     else if(method == "GET"){
     // request method is GET
     DefaultHttpClient httpClient = new DefaultHttpClient();
         String paramString = URLEncodedUtils.format(params, "utf-8");
         url += "?" + paramString; HttpGet httpGet = new HttpGet(url);
         HttpResponse httpResponse = httpClient.execute(httpGet);
         HttpEntity httpEntity = httpResponse.getEntity(); is = httpEntity.getContent(); }
     }
     catch (UnsupportedEncodingException e)
     { e.printStackTrace(); }
     catch (ClientProtocolException e)
     { e.printStackTrace(); }
     catch (IOException e)
     { e.printStackTrace();
     }
        try
        { BufferedReader reader = new BufferedReader(new InputStreamReader( is));
            StringBuilder str = new StringBuilder();
            String strLine = null;
            while ((strLine = reader.readLine()) != null) {
                str.append(strLine + "\n");
            }

        is.close();
        json = str.toString();
        }
        catch (Exception e) { }
        // now will try to parse the string into JSON object
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
        }
        return jsonObj;
    }
      }
      */

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";


    // constructor
    public JSONParser() {

    }

    // function get json from url
// by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {

        // Making HTTP request
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                HttpResponse httpResponse = null;
                try {
                    httpResponse = httpClient.execute(httpPost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}