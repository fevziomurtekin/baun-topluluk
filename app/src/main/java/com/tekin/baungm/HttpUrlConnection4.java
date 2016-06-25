package com.tekin.baungm;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ömür on 23.06.2016.
 */
public class HttpUrlConnection4 extends AsyncTask {
    String paremeters;
    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public HttpUrlConnection4(String paremeters){
        this.paremeters=paremeters;
    }



    private String sendGet() throws Exception {

        String url = "http://localhost:8080/cek.php";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return url;
    }

    // HTTP POST request
    public String sendPost(String url, String urlParameters) throws Exception {


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }

    @Override

    protected Object doInBackground(Object[] params) {
        String post=" ";


        try {
            post = sendPost("http://192.168.56.1:8080/etkinlikkatilim.php", paremeters);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return post;
    }
    protected String onPostExecute(Object[] params){
        String get=" ";
        try{
            get=sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return get;
    }





}

