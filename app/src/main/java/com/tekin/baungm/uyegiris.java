package com.tekin.baungm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ömür on 10.06.2016.
 */
public class uyegiris extends Activity {
    protected EditText username;
    private EditText password;
    protected String enteredUsername;
    String final_username="";
    String enteredPassword;
    String uye_ismi;
    private final String serverUrl = "http://192.168.56.1:8080/user1.php";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyegiris);
        username = (EditText)findViewById(R.id.adigir);
        password = (EditText)findViewById(R.id.ogrencigir);
        Button loginButton = (Button)findViewById(R.id.giris);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        uye_ismi=pref.getString("username","");
        editor = pref.edit();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredUsername = username.getText().toString();
                enteredPassword = password.getText().toString();
                HashMap data = new HashMap();
                if(enteredUsername.equals("") || enteredPassword.equals("")){
                    Toast.makeText(uyegiris.this, "Username or password must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                if(enteredUsername.length() <= 1 || enteredPassword.length() <= 1){
                    Toast.makeText(uyegiris.this, "Username or password length must be greater than one", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!uye_ismi.equals("")){
                    Toast.makeText(getApplicationContext(),"Zaten giriş yapmış bulunmaktasınız..",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Anasayfaya yönlendiriliyorsunuz",Toast.LENGTH_LONG).show();
                }
// request authentication with remote server4
                data.put("txtUsername",enteredUsername);
                data.put("txtPassword", enteredPassword);
                AsyncDataClass asyncRequestObject = new AsyncDataClass();
                asyncRequestObject.execute(serverUrl, enteredUsername, enteredPassword);
            }
        });

    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(params[0]);
            String jsonResult = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", params[1]));
                nameValuePairs.add(new BasicNameValuePair("password", params[2]));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if(result.equals("") || result == null){
                Toast.makeText(uyegiris.this, "Server connection failed", Toast.LENGTH_LONG).show();
                return;
            }
            int jsonResult = returnParsedJsonObject(result);
            if(jsonResult == 0){
                Toast.makeText(uyegiris.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                return;
            }
            if(jsonResult == 1){

                if(enteredUsername.equals("omur tekin")){
                    editor.putString("username",enteredUsername);
                    editor.putString("password",enteredPassword);
                    editor.apply();
                    final_username="Admin";
                    String parameters = "&txtName=" +enteredUsername + "&txtTel=" + enteredPassword;
                    HttpUrlConnection3 httpUrlConnection = new HttpUrlConnection3(parameters);
                    httpUrlConnection.execute();
                Intent intent = new Intent(uyegiris.this, LoginActivity.class);
                intent.putExtra("USERNAME", final_username);
                intent.putExtra("MESSAGE", "Başarılı Giriş");
                startActivity(intent);
            }
                else{
                    editor.putString("username",enteredUsername);
                    editor.putString("password",enteredPassword);
                    editor.apply();
                    String parameters = "&txtName=" +enteredUsername + "&txtTel=" + enteredPassword;
                    HttpUrlConnection3 httpUrlConnection = new HttpUrlConnection3(parameters);
                    httpUrlConnection.execute();
                    final_username=enteredUsername;
                    Intent intent = new Intent(uyegiris.this, LoginActivity.class);
                    intent.putExtra("USERNAME", final_username);
                    intent.putExtra("MESSAGE", "succes");
                    startActivity(intent);
                }
            }
        }
        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }
    }
    private int returnParsedJsonObject(String result){
        JSONObject resultObject = null;
        int returnedResult = 0;
        try {
            resultObject = new JSONObject(result);
            returnedResult = resultObject.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnedResult;
    }
}