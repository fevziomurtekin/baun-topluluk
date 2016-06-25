package com.tekin.baungm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ömür on 24.06.2016.
 */
public class AdminKatilim extends Activity implements Download_data_katilim.download_complete {
    public ListView list;
    public ArrayList<KatilimCek> gezi = new ArrayList<KatilimCek>();
    public ListAdapterKatilim adapter;
     int post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_katilan);
        list= (ListView) findViewById(R.id.listkatilan);
        adapter=new ListAdapterKatilim(AdminKatilim.this);
        list.setAdapter(adapter);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("İşlem Ekranı");
        builder.setMessage("Etkinliğe katılmasını onaylıyor musunuz?");
        builder.setCancelable(false);

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* Burada herhangi bir işlem yapılmayacak*/
                Toast.makeText(getApplicationContext(),"Katılım Onaylandı",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), AdminKatilim.class);
                startActivity(i);

            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Katılım onaylanmadı..", Toast.LENGTH_SHORT).show();
                int malzeme_id= (int) adapter.getItemId(post);
                String parameters="&id="+malzeme_id;
                HttpConnectionSil deneme=new HttpConnectionSil(parameters);
                deneme.execute();
                Intent i = new Intent(getApplicationContext(), AdminKatilim.class);
                startActivity(i);
            }
        });
        final Download_data_katilim download_data = new Download_data_katilim((Download_data_katilim.download_complete) this);
        download_data.download_data_from_link("http://192.168.56.1:8080/katilimcek.php");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 post=position;
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void get_data(String data) {
        try {
            JSONArray data_array=new JSONArray(data);


            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                KatilimCek add=new KatilimCek();
                add.setAdsoyad(obj.getString("adsoyad"));
                add.setOgrenciNumarasi(obj.getString("ogrenci_numarasi"));
                add.setEtkinlikAdi(obj.getString("etkinlik_adi"));



                gezi.add(add);

            }

            adapter.notifyDataSetInvalidated();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }

