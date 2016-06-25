package com.tekin.baungm;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class WhoAreWe extends AppCompatActivity
         {
public ArrayList<Example> veri=new ArrayList<Example>();
        TextView baslik_adi,giris_adi;
        ImageView image_adi;

    final static String URL="http://192.168.56.1:8080/bizkimiz.php";
    JSONObject json;
    String baslik,resim,yazi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_whoarewe);


        baslik_adi = (TextView) findViewById(R.id.baslik_ana);
        giris_adi = (TextView) findViewById(R.id.giris_ana);
        image_adi = (ImageView) findViewById(R.id.image_ana);
        new Veri().execute("text");
    }

    protected JSONObject vericekme() throws IOException, JSONException {

        HttpClient client=new DefaultHttpClient();
        HttpGet get=new HttpGet(URL);
        HttpResponse response=client.execute(get);
        StatusLine status=response.getStatusLine();// status kodu aldık
        int s=status.getStatusCode(); //bu status code 200 ise dogru yollanmıştır.

        if(s==200){

            HttpEntity e=response.getEntity();
            String data= EntityUtils.toString(e);
            JSONArray post=new JSONArray(data);
            int uzunluk=post.length()-1;
            JSONObject last=post.getJSONObject(uzunluk);// ilk objeyi çağıracak.
            return last;

        }

        return null;
    }
    public class Veri extends AsyncTask<String,String,String> {

        @Override
        protected void onPostExecute(String data) {
            String [] veriler=data.split("/",3);
            String baslik_veri=veriler[0];
            String yazi_veri=veriler[1];
            String resim_veri=veriler[2];
            baslik_adi.setText(baslik_veri);
            giris_adi.setText(yazi_veri);
            byte []image_data= Base64.decode(resim_veri, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(image_data, 0, image_data.length);
            image_adi.setImageBitmap(bitmap);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                json=vericekme();
                baslik=json.getString("baslik");
                yazi=json.getString("yazi");
                resim=json.getString("resim");
                String data= baslik+"/"+yazi+"/"+resim;
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}



