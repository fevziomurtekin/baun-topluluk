package com.tekin.baungm;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Ömür on 20.06.2016.
 */
public class Etkinlik extends TabActivity {
    TabHost tabHost;
    TabHost.TabSpec tabYeni , tabOnce ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etkinlik);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabOnce=tabHost.newTabSpec("Yapmış olduğumuz etkinlikler");
        tabOnce.setIndicator("Önceden Yapmış olduğumuz etkinlikler",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, OnceEtkinlik.class));

        tabYeni=tabHost.newTabSpec("Yapacağımız Etkinlikler");
        tabYeni.setIndicator("Yapacağımız Etkinlikler",
                getResources().getDrawable(R.drawable.ic_menu_gallery))
                .setContent(new Intent(this, YeniEtkinlik.class));

        tabHost.addTab(tabOnce);
        tabHost.addTab(tabYeni);
        tabHost.setCurrentTab(1);
    }
}
