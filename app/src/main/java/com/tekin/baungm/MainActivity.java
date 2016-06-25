package com.tekin.baungm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ArrayList<Example> veri = new ArrayList<Example>();
    TextView baslik_adi, giris_adi;
    ImageView image_adi;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String login = "false";
    String mesaj = "";

    final static String URL = "http://192.168.56.1:8080/anasayfa.php";
    final static String URI = "http://192.168.56.1:8080/uyegirisicekme.php";
    JSONObject json,jsonuye;
    public String uye_ismi;
    String baslik, resim, yazi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        baslik_adi = (TextView) findViewById(R.id.baslik_ana);
        giris_adi = (TextView) findViewById(R.id.giris_ana);
        image_adi = (ImageView) findViewById(R.id.image_ana);
        new Veri().execute("text");
      //  new uyeveri().execute("text");
        //String base=example.getResim();
        /*byte [] data= Base64.decode(base, Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
        image_adi.setImageBitmap(bitmap);*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        uye_ismi=pref.getString("username", "");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem giornataItem = menu.findItem(R.id.action_kullanici);
        MenuItem logout=menu.findItem(R.id.logout);

        giornataItem.setTitle(uye_ismi);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("İşlem Ekranı");
        builder.setMessage("İşlemi Onaylıyor musunuz?");
        builder.setCancelable(false);

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
                giornataItem.setTitle("GİRİS YAPIN");
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Anasayfaya yönlendiriliyorsunuz..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        Intent intent = getIntent();
        final Bundle intentBundle = intent.getExtras();
        giornataItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (uye_ismi.equals("")) {
                    giornataItem.setTitle("GİRİS YAPINIZ");
                    Intent giris = new Intent(getApplicationContext(), uyegiris.class);
                    startActivity(giris);
                    Toast.makeText(getApplicationContext(), "Giris sayfasina yonlendiriliyorsunuz", Toast.LENGTH_SHORT).show();
                } else if(uye_ismi.equals("omur tekin")){
                    try {
                        giornataItem.setTitle(uye_ismi);
                        Intent giris = new Intent(getApplicationContext(), adminpanel.class);
                        startActivity(giris);
                        Toast.makeText(getApplicationContext(), "Admin paneline yönlendiriliyorsunuz..", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Uye girisi yapmış bulunmaktasınız..",Toast.LENGTH_LONG).show();
                    giornataItem.setTitle(uye_ismi);}
                return true;
            }
        });
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kimiz) {
            Intent kim = new Intent(getApplicationContext(), WhoAreWe.class);
            startActivity(kim);
        } else if (id == R.id.etkinlik) {
            Intent etkinlik = new Intent(getApplicationContext(), Etkinlik.class);
            startActivity(etkinlik);
        } else if (id == R.id.uye) {
            Intent uye = new Intent(getApplicationContext(), kayitol.class);
            startActivity(uye);
        } else if (id == R.id.giris) {
            Intent giris = new Intent(getApplicationContext(), uyegiris.class);
            startActivity(giris);
        } else if (id == R.id.paylas) {
            share();
        } else if (id == R.id.gonder) { Intent giris = new Intent(getApplicationContext(), Mesaj.class);
            startActivity(giris);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected JSONObject vericekme() throws IOException, JSONException {

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL);
        HttpResponse response = client.execute(get);
        StatusLine status = response.getStatusLine();// status kodu aldık
        int s = status.getStatusCode(); //bu status code 200 ise dogru yollanmıştır.

        if (s == 200) {

            HttpEntity e = response.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray post = new JSONArray(data);
            int uzunluk = post.length() - 1;
            JSONObject last = post.getJSONObject(uzunluk);// ilk objeyi çağıracak.
            return last;

        }

        return null;
    }

    public class Veri extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String data) {
            String[] veriler = data.split("/", 3);
            String baslik_veri = veriler[0];
            String yazi_veri = veriler[1];
            String resim_veri = veriler[2];
            baslik_adi.setText(baslik_veri);
            giris_adi.setText(yazi_veri);
            byte[] image_data = Base64.decode(resim_veri, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image_data, 0, image_data.length);
            image_adi.setImageBitmap(bitmap);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                json = vericekme();
                baslik = json.getString("baslik");
                yazi = json.getString("yazi");
                resim = json.getString("resim");
                String data = baslik + "/" + yazi + "/" + resim;
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void share(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); //Share intentini oluşturuyoruz
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mesaj Konu");//share mesaj konusu
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "mobilhanem.com tarafından gönderildi");//share mesaj içeriği
        startActivity(Intent.createChooser(sharingIntent, "Paylaşmak İçin Seçiniz"));//Share intentini başlığı ile birlikte başlatıyoruz
    }

}

