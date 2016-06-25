package com.tekin.baungm;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ömür on 24.06.2016.
 */
public class adminMesaj extends Activity implements Download_data_mesaj.download_complete {
    public ListView list;
    public ArrayList<MesajCek> gezi = new ArrayList<MesajCek>();
    public ListAdapterMesaj adapter;
    int post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mesaj);
        list= (ListView) findViewById(R.id.listmesaj);
        adapter=new ListAdapterMesaj(adminMesaj.this);
        list.setAdapter(adapter);
        final Download_data_mesaj download_data = new Download_data_mesaj((Download_data_mesaj.download_complete) this);
        download_data.download_data_from_link("http://192.168.56.1:8080/mesajcek.php");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog=new Dialog(adminMesaj.this);
                dialog.setContentView(R.layout.mesaj_dialog);
                TextView gonderen= (TextView) dialog.findViewById(R.id.gonderen_dialog);
                TextView mesaj= (TextView) dialog.findViewById(R.id.mesaj_dialog);
                TextView tarih= (TextView) dialog.findViewById(R.id.tarih_dialog);
                Button tamam= (Button) dialog.findViewById(R.id.dialog_tamam);
                gonderen.setText(adapter.mesaj.gezi.get(position).getMesajGonderen());
                mesaj.setText(adapter.mesaj.gezi.get(position).getMesaj());
                tarih.setText(adapter.mesaj.gezi.get(position).getTarih());
                tamam.setOnClickListener(new View.OnClickListener() {
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
                MesajCek add=new MesajCek();
                add.setMesajGonderen(obj.getString("mesaj_gonderen"));
                add.setMesaj(obj.getString("mesaj"));
                add.setTarih(obj.getString("tarih"));


                gezi.add(add);

            }

            adapter.notifyDataSetInvalidated();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}