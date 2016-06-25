<?php
header('Content-Type: text/html; charset=utf-8');
$db_host="localhost";
$db_username="root";
$db_password="";
$db_name="girisimcimuhendis";
$baglanti=mysqli_connect("$db_host","$db_username","$db_password","$db_name");
mysqli_connect("$db_host","$db_username","$db_password","$db_name")or die ("Host'a Baglanamadim");
if($_POST)
{
$id=$_POST["id"];
$sorgu=mysqli_query($baglanti,"DELETE FROM `katilim` WHERE id='$id'");
 if ($sorgu){
        echo "Kayıt silindi";
    }else{
	    echo "Kayıt Silinemedi";
    }   
}else{//post metodu ile değer gönderilmediyse
    $sorgu=mysqli_query($baglanti,"SELECT * FROM `katilim`");//Tüm kayıtları seç
    echo '<table>';
    echo '<tr><td>Malzeme adi</td><td>Malzeme resmi</td><td>Aciklama</td><td>Silinsin mi?</td></tr>';
    $sayac=1;//Formların isimleri farklı olsun diye kullanılacak
    while($kayit=mysqli_fetch_array($sorgu)){
	    echo '<form action="" method="post" name="form'.$sayac.'">';
	    echo '<tr>';
    	echo '<td>'.$kayit["adsoyad"].'</td>';
    	echo '<td>'.$kayit["ogrenci_numarasi"].'</td>';
    	echo '<td>'.$kayit["etkinlik_adi"].'</td>';
    	echo '<td><input type="submit" name="gonder" value="Evet"/></td>';
    	echo '<input type="hidden" name="id" value="'.$kayit["id"].'"/>';
    	echo '</tr>';
    	echo '</form>';
        $sayac++;
    }
    echo '</table>';
}



?>