package com.tekin.baungm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Ömür on 6.06.2016.
 */
public class kayitol extends ActionBarActivity {
    EditText adi,email,telefon,bilgi,numara;
    Spinner spinner;
    Button kayit;
    String bolum_adi;
    private String[] bolumler=new String[]{"Makine Mühendisliği","Endüstri Mühendisliği","Metalurji ve Malzeme Mühendisliği","Çevre mühendisliği","Otomotiv mühendisliği","İnşaat Mühendisliği","Tekstil Mühendisliği","Bilgisayar Mühendisliği","Elektrik ve Elektronik Mühendisliği","Yazılım Mühendisliği"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitol);
     adi= (EditText) findViewById(R.id.adigir);
        email= (EditText) findViewById(R.id.emailgir);
        numara= (EditText) findViewById(R.id.ogrencigir);
        telefon= (EditText) findViewById(R.id.telefongir);
        bilgi= (EditText) findViewById(R.id.bilgigir);
        spinner= (Spinner) findViewById(R.id.bölümgir);
        kayit= (Button) findViewById(R.id.kayitol);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.bolum,bolumler);
        arrayAdapter.setDropDownViewResource(R.layout.bolum);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int bolum_id = position;
                if (bolum_id == 1) {
                    bolum_adi = "Makine Mühendisliği";
                }
                if (bolum_id == 2) {
                    bolum_adi = "Endüstri Mühendisliği";
                }
                if (bolum_id == 3) {
                    bolum_adi = "Metalurji ve Malzeme Mühendisliği";
                }
                if (bolum_id == 4) {
                    bolum_adi = "Çevre mühendisliği";
                }
                if (bolum_id == 5) {
                    bolum_adi = "Otomotiv mühendisliği";
                }
                if (bolum_id == 6) {
                    bolum_adi = "Tekstil Mühendisliği";
                }
                if (bolum_id == 7) {
                    bolum_adi = "Bilgisayar Mühendisliği";
                }
                if (bolum_id == 8) {
                    bolum_adi = "Elektrik ve Elektronik Mühendisliği";
                }
                if (bolum_id == 9) {
                    bolum_adi = "Yazılım Mühendisliği";
                }
            }


        // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            kayit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ad = adi.getText().toString();
                    String phone = telefon.getText().toString();
                    String bilgim = bilgi.getText().toString();
                    String emailim = email.getText().toString();
                    String ogrenci_numarasi=numara.getText().toString();
                    if (ad.equals("") || phone.equals("") || bilgim.equals("") || emailim.equals("") || ogrenci_numarasi.equals("")) {
                        Toast.makeText(getApplicationContext(), "Eksik alanlari doldurunuz..", Toast.LENGTH_LONG).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } else {
                        String parameters = "&txtName=" + ad + "&txtTel=" + ogrenci_numarasi + "&txtMajor=" + emailim + "&txtBolum=" + bolum_adi+"$txtTelefon="+phone+"txtBilgi="+bilgim;
                        HttpUrlConnection httpUrlConnection = new HttpUrlConnection(parameters);
                        httpUrlConnection.execute();
                        Toast.makeText(kayitol.this, "Topluluğa kayıt olunuyor", Toast.LENGTH_SHORT).show();
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(3000);
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
            });


        }


    }



