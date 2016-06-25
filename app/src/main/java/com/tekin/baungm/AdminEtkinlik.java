package com.tekin.baungm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Created by Ömür on 10.06.2016.
 */
public class AdminEtkinlik extends Activity {
    private static final int RESULT_LOAD_IMG = 1;
    private String imgDecodableString;
    private String senttoservices;
    private String image;
    String s_baslik,s_yazi,s_date,date;
    EditText baslik,yazi,e_date;
    Button onayla;
    ImageButton resim_sec;
    ImageButton resim_yukle;
    Button tarih;
    ImageView resim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_etkinlik);
        baslik= (EditText) findViewById(R.id.baslikgir);
        yazi= (EditText) findViewById(R.id.girisgir);
        resim_sec= (ImageButton) findViewById(R.id.resim);
        resim= (ImageView) findViewById(R.id.yuklenenresim);
        resim_yukle= (ImageButton) findViewById(R.id.resim_yukle);
        tarih= (Button) findViewById(R.id.time);

        onayla= (Button) findViewById(R.id.onayla);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("İşlem Ekranı");
        builder.setMessage("Başka işlem yapmak istiyor musun,Admin?");
        builder.setCancelable(false);

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), adminpanel.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Anasayfaya yönlendiriliyorsunuz..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        resim_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery();

            }
        });
        resim_yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (senttoservices == null || senttoservices.equals("")) {
                    Toast.makeText(getApplicationContext(), "No Image Selected.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), senttoservices, Toast.LENGTH_LONG).show();
                    byte[] data = Base64.decode(senttoservices, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    resim.setImageBitmap(bmp);
                }
            }
        });
        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(AdminEtkinlik.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                         date=year+"-"+ monthOfYear+ "-"+ dayOfMonth ;//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                    }
                },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.show();

            }
        });
        onayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_baslik=baslik.getText().toString();
                s_yazi=yazi.getText().toString();

                if(s_baslik.equals("") || s_yazi.equals("") ){
                    Toast.makeText(getApplicationContext(),"Baslik veya Yazi ve Tarih girmedin!",Toast.LENGTH_LONG).show();
                }
                else if(s_baslik.equals("") && s_yazi.equals("") ){
                    Toast.makeText(getApplicationContext(),"Baslik ve Yazi ve Tarih girmedin!",Toast.LENGTH_LONG).show();
                }
                else{
                    String parameters = "&txtName=" + s_baslik + "&txtTel=" + s_yazi + "&txtDate=" + date  + "&txtMajor=" + image  ;

                    HttpUrlConnection2 httpUrlConnection = new HttpUrlConnection2(parameters);
                    httpUrlConnection.execute();
                    AlertDialog dialog=builder.create();
                    dialog.show();

                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode == RESULT_LOAD_IMG && resultCode == -1 && data != null) {
                Uri e = data.getData();
                String[] filePathColumn = new String[]{"_data"};
                Cursor cursor = this.getContentResolver().query(e, filePathColumn, (String) null, (String[]) null, (String) null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                this.imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bit= BitmapFactory.decodeFile(this.imgDecodableString);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.JPEG,90, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                final String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                String s = URLEncoder.encode(encoded, "UTF-8");
                senttoservices=encoded;
                image=s;
                System.out.print(s);
                Toast.makeText(getApplicationContext(), encoded, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "resim seçilmedi", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            Toast.makeText(this, "resim alınırken bir hatayla karsılasıldı", Toast.LENGTH_SHORT).show();
        }


    }

    private void loadImagefromGallery() {
        Intent galleryIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }


}

