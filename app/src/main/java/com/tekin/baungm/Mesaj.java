package com.tekin.baungm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ömür on 24.06.2016.
 */
public class Mesaj extends Activity {
    TextView uye_adi;
    EditText mesaj;
    Button gonder;
    String uye_ismi;
    String date;
    String uye_mesaji;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesaj_gonder);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        uye_ismi=pref.getString("username", "");
        uye_adi= (TextView) findViewById(R.id.uyeadi);
        mesaj= (EditText) findViewById(R.id.mesajın);
        gonder= (Button) findViewById(R.id.gonder);
        SimpleDateFormat bicim2=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date tarihSaat=new Date();
        date=bicim2.format(tarihSaat);
        if(uye_ismi.equals("")){uye_adi.setText("Merhaba uyemiz");}
        else{uye_adi.setText("Merhaba "+uye_ismi);}

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uye_mesaji=mesaj.getText().toString();
                String parameters = "&txtName=" + uye_mesaji + "&txtTel=" + uye_ismi + "&txtMajor=" +date;
                HttpUrlConnection5 deneme=new HttpUrlConnection5(parameters);
                deneme.execute();
                Toast.makeText(getApplicationContext(),"Mesajınız iletilmiştir.Görüşleriniz ve önerileriniz için teşekkür ederiz...",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Anasayfaya yönlendiriliyorsunuz",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
