package com.tekin.baungm;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by Ömür on 9.06.2016.
 */
public class adminpanel extends TabActivity {
    TabHost tabHost;
    TabHost.TabSpec tabAnasayfa, tabEtkinlik, tabBizkimiz,tabKatilan,tabMesaj;
    ImageButton cikis;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpanel);
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        cikis= (ImageButton) findViewById(R.id.exit);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                editor = pref.edit();
                                editor.clear();
                                editor.commit();
                                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Admin Panelinden çıkış yapılıyor..", Toast.LENGTH_SHORT).show();
            }
        });
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabAnasayfa = tabHost.newTabSpec("Anasayfa");
        tabAnasayfa.setIndicator("Anasayfa",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, AdminAnasayfa.class));

        tabEtkinlik = tabHost.newTabSpec("Etkinlik");
        tabEtkinlik.setIndicator("Etkinlik",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, AdminEtkinlik.class));

        tabBizkimiz = tabHost.newTabSpec("Biz kimiz");
        tabBizkimiz.setIndicator("Biz Kimiz",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, AdminBizkimiz.class));

        tabKatilan = tabHost.newTabSpec("Katilim");
        tabKatilan.setIndicator("Katilim",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, AdminKatilim.class));

        tabMesaj = tabHost.newTabSpec("Mesaj");
        tabMesaj.setIndicator("Mesaj",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, adminMesaj.class));

        tabHost.addTab(tabAnasayfa);
        tabHost.addTab(tabEtkinlik);
        tabHost.addTab(tabBizkimiz);
        tabHost.addTab(tabKatilan);
        tabHost.addTab(tabMesaj);
        tabHost.setCurrentTab(1);

    }
}




