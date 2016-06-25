<?php

/**
 * Mysql veri tabanından veri çekmek
 * http://www.umutsinav.com
 * @umutsinav
 * @package php_dersleri
 */
//türkçe karakter hatası vermemesi için sayfanın kodlamasını utf8 olarak ayarlıyoruz

//Veri Tabanına baglantı kodlarımız
$db_host="localhost";
$db_username="root";
$db_password="";
$db_name="girisimcimuhendis";
$baglanti=mysqli_connect("$db_host","$db_username","$db_password","$db_name");
mysqli_connect("$db_host","$db_username","$db_password","$db_name")or die ("Host'a Baglanamadim");
header('Content-Type: text/html; charset=utf-8');
mysqli_query($baglanti,"SET NAMES 'utf8'");
mysqli_query($baglanti,"SET CHARACTER SET latin5");
 
//Tüm kayıtları çekiyoruz
$tarih=date('Y-m-d');
$sorgu=mysqli_query($baglanti,"SELECT * FROM etkinlik WHERE date <= $tarih ORDER BY `id` ASC");
 $ogrenci=array();
 $dizi=array();
     function json_turkce($dizi){
      foreach($dizi as $record){
        foreach($record as $key=>$og){
          $colm[]='"'.$key.'":"'.$og.'"';
        }
        $rec[]='{'.implode(',', $colm).'}';
        unset($colm);
      }
      
      $sonuc='['.implode(',', $rec).']';
      return $sonuc;
     }
while(true)
{
	
	if($ogrenci=mysqli_fetch_assoc($sorgu))
{
array_push($dizi,$ogrenci);
	
}
		else{break;}
}
print(json_turkce($dizi));
?>
