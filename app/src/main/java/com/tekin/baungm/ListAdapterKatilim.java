package com.tekin.baungm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ömür on 24.06.2016.
 */
public class ListAdapterKatilim extends BaseAdapter {
    AdminKatilim katilim;

    ListAdapterKatilim(AdminKatilim katilim){this.katilim=katilim;
    }
    @Override
    public int getCount() {
        return katilim.gezi.size();
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
        TextView numara;
        TextView giris;
        Button onayla;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder=new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) katilim.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_katilan, null);

            holder.adsoyad = (TextView) convertView.findViewById(R.id.adsoyadgir);
            holder.numara = (TextView) convertView.findViewById(R.id.ogrencinumarasigir);
            holder.giris= (TextView) convertView.findViewById(R.id.baslikgir);
            holder.onayla= (Button) convertView.findViewById(R.id.onayla);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.adsoyad.setText(this.katilim.gezi.get(position).getAdsoyad());
        holder.numara.setText(this.katilim.gezi.get(position).getOgrenciNumarasi());
        holder.giris.setText(this.katilim.gezi.get(position).getEtkinlikAdi());
        return convertView;
    }
}
