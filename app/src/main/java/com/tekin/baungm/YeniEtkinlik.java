package com.tekin.baungm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ömür on 20.06.2016.
 */
public class YeniEtkinlik extends Activity implements Download_data_yeni.download_complete {
    public ListView list;
    public ArrayList<Example> gezi = new ArrayList<Example>();
    public ListAdapterYeni adapter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String uye_ismi,uye_numarasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etkinlik_yeni);
        list= (ListView) findViewById(R.id.listyeni);
        adapter=new ListAdapterYeni(YeniEtkinlik.this);
        list.setAdapter(adapter);
        final Download_data_yeni download_data = new Download_data_yeni((Download_data_yeni.download_complete) this);
        download_data.download_data_from_link("http://192.168.56.1:8080/etkinliksonra.php");
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        uye_ismi=pref.getString("username", "");
        uye_numarasi=pref.getString("password","");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog=new Dialog(YeniEtkinlik.this);
                dialog.setContentView(R.layout.etkinlik_dialog_yeni);
                ImageView dialog_resim= (ImageView) dialog.findViewById(R.id.image_dialog);
                TextView dialog_baslik= (TextView) dialog.findViewById(R.id.baslik_dialog);
                TextView dialog_giris= (TextView) dialog.findViewById(R.id.giris_dialog);
                String base=adapter.etkinlik.gezi.get(position).getImage();
                byte [] data= Base64.decode(base, Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length);
                dialog_resim.setImageBitmap(bitmap);
                final String etkinlik_adi=adapter.etkinlik.gezi.get(position).getBaslik();
                dialog_baslik.setText(adapter.etkinlik.gezi.get(position).getBaslik());
                dialog_giris.setText(adapter.etkinlik.gezi.get(position).getYazi());
                final int post=position;
                Button dialog_tamam= (Button) dialog.findViewById(R.id.dialog_tamam);
                Button dialog_katil= (Button) dialog.findViewById(R.id.dialog_katil);
            dialog_tamam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
                dialog_katil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      String parameters="&txtName=" + uye_ismi + "&txtTel=" + uye_numarasi+"&txtBaslik="+etkinlik_adi;
                        HttpUrlConnection4 deneme=new HttpUrlConnection4(parameters);
                        deneme.execute();
                        Toast.makeText(getApplicationContext(), "Katılma isteğiniz kayda alınmıştır.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
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
                Example add=new Example();
                add.setBaslik(obj.getString("baslik"));
                add.setImage(obj.getString("image"));
                add.setYazi(obj.getString("yazi"));
                add.setDate(obj.getString("date"));


                gezi.add(add);

            }

            adapter.notifyDataSetInvalidated();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
