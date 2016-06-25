package com.tekin.baungm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Ömür on 24.06.2016.
 */
public class ListAdapterMesaj extends BaseAdapter {
    adminMesaj mesaj;
    ListAdapterMesaj(adminMesaj mesaj){this.mesaj=mesaj;}
    @Override
    public int getCount() {
        return mesaj.gezi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolderItem {

        TextView adsoyad;
        TextView mesaj;
        TextView tarih;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder=new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mesaj.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_mesaj, null);
            holder.adsoyad= (TextView) convertView.findViewById(R.id.adsoyadgir);
            holder.mesaj= (TextView) convertView.findViewById(R.id.mesajgir);
            holder.tarih= (TextView) convertView.findViewById(R.id.tarihsaatgir);
            convertView.setTag(holder);
        }else{

            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.adsoyad.setText(this.mesaj.gezi.get(position).getMesajGonderen());
        holder.mesaj.setText(this.mesaj.gezi.get(position).getMesaj());
        holder.tarih.setText(this.mesaj.gezi.get(position).getTarih());
        return convertView;
    }
}
