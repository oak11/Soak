package com.example.shweta.soak;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends ActionBarActivity implements View.OnClickListener {
    Button bLogin;
    TextView registerLink;
    EditText etUsername, etPassword;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        registerLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(username, password);

                authenticate(user);
                break;
            case R.id.tvRegisterLink:
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, MainActivity.class));
    }
}

/*
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
     EditText user, pass;
    Button bLogin;
     // Progress Dialog
     private ProgressDialog pDialog;
      // JSON parser class
      JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://oak.web44.net.com/Register.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login2);
    user = (EditText)findViewById(R.id.etUsername);
        pass = (EditText)findViewById(R.id.etPassword);
        bLogin = (Button)findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.bLogin:
             new AttemptLogin().execute();
             // here we have used, switch case, because on login activity you may
             // also want to show registration button, so if the user is new ! we can go the
             // registration activity , other than this we could also do this without switch
             // case.
             default: break; }
}
    class AttemptLogin extends AsyncTask<String, String, String> {
        // Before starting background thread Show Progress Dialog
        boolean failure = false;
        @Override protected void onPreExecute() {
            super.onPreExecute();

        pDialog = new ProgressDialog(Login.this);
        pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true); pDialog.show();
        }

        @Override protected String doInBackground(String... args)
        { // TODO Auto-generated method stub
        // here Check for success tag
        int success; String username = user.getText().toString();
            String password = pass.getText().toString();
            try { List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");

                JSONObject json = jsonParser.makeHttpRequest( "http://oak.web44.net", "POST", params);

                 // checking log for json response
                 Log.d("Login attempt", json.toString());
                 // success tag for json
                 success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                { Log.d("Successfully Login!", json.toString());
                    Intent ii = new Intent(Login.this,Wall.class); finish();
                     // this finish() method is used to tell android os that we are done with current
                     // activity now! Moving to other activity
                     startActivity(ii);
                    return json.getString(TAG_MESSAGE); }
                else{ return json.getString(TAG_MESSAGE); } }
            catch (JSONException e) { e.printStackTrace(); } return null; }

        // Once the background process is done we need to Dismiss the progress dialog asap
        protected void onPostExecute(String message)
        { pDialog.dismiss(); if (message != null){ Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show(); } } } }

*/


