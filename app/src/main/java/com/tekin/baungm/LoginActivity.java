package com.tekin.baungm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Ömür on 9.06.2016.
 */
public class LoginActivity extends ActionBarActivity {

    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
         String loggedUser = intentBundle.getString("USERNAME");
        try {
            loggedUser = capitalizeFirstCharacter(loggedUser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String message = intentBundle.getString("MESSAGE");
        final TextView loginUsername = (TextView) findViewById(R.id.login_user);
        TextView successMessage = (TextView) findViewById(R.id.message);
        loginUsername.setText(loggedUser);
        successMessage.setText(message);
        final String finalLoggedUser = loggedUser;
        final String finalMessage=message;
        if (finalLoggedUser != null) {
            if(finalLoggedUser.equals("Admin")) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(3000);
                                Intent i = new Intent(getApplicationContext(), adminpanel.class);

                                startActivity(i);
                            }
                        } catch (InterruptedException ex) {
                        }

                        // TODO
                    }
                };

                thread.start();
            }
            else{
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(3000);
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("MESSAGE",finalMessage);
                                startActivity(i);
                            }
                        } catch (InterruptedException ex) {
                        }

                        // TODO
                    }
                };

                thread.start();
            }
        }
    }

    private String capitalizeFirstCharacter(String textInput) throws InterruptedException {
        String input = textInput.toLowerCase();
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;    }





}