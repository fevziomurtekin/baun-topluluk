package com.tekin.baungm;

import android.app.Activity;
import android.app.Dialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ömür on 20.06.2016.
 */
public class OnceEtkinlik extends Activity implements Download_data_once.download_complete {
    public ListView list;
    public ArrayList<Example> gezi = new ArrayList<Example>();
    public ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etkinlik_once);
        list = (ListView) findViewById(R.id.listonce);
        adapter=new ListAdapter(OnceEtkinlik.this);
        list.setAdapter(adapter);
        final Download_data_once download_data = new Download_data_once((Download_data_once.download_complete) this);
        download_data.download_data_from_link("http://192.168.56.1:8080/etkinlikonce.php");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog=new Dialog(OnceEtkinlik.this);
                dialog.setContentView(R.layout.etkinlik_dialog_once);

                ImageView dialog_resim= (ImageView) dialog.findViewById(R.id.image_dialog);
                TextView dialog_baslik= (TextView) dialog.findViewById(R.id.baslik_dialog);
                TextView dialog_giris= (TextView) dialog.findViewById(R.id.giris_dialog);
                String base=adapter.etkinlik.gezi.get(position).getImage();
                byte [] data= Base64.decode(base, Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length);
                dialog_resim.setImageBitmap(bitmap);
                dialog_baslik.setText(adapter.etkinlik.gezi.get(position).getBaslik());
                dialog_giris.setText(adapter.etkinlik.gezi.get(position).getYazi());
                final int post=position;
                Button dialog_button= (Button) dialog.findViewById(R.id.dialog_tamam);
                dialog_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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