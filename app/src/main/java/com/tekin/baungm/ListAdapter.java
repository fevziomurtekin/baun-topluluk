package com.tekin.baungm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ömür on 20.06.2016.
 */
public class ListAdapter  extends BaseAdapter {

    OnceEtkinlik etkinlik;

    ListAdapter(OnceEtkinlik etkinlik)
    {
        this.etkinlik = etkinlik;
    }



    @Override
    public int getCount() {
        return  etkinlik.gezi.size();
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
        TextView name;
        ImageView major;
        TextView giris;
        TextView date;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) etkinlik.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell, null);

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.major = (ImageView) convertView.findViewById(R.id.resim);
            holder.giris= (TextView) convertView.findViewById(R.id.giris_dialog);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }


        holder.name.setText(this.etkinlik.gezi.get(position).getBaslik());



        // byte[] data = Base64.decode(this.malzeme.malzemeler.get(position).getMalzemeAdi(), Base64.DEFAULT);
        String base=this.etkinlik.gezi.get(position).getImage();
        byte [] data=Base64.decode(base, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        //  BitmapDrawable k=new BitmapDrawable(bmp);
        holder.major.setImageBitmap(bmp);


        return convertView;
    }

}
