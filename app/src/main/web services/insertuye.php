<?PHP
if(isset($_POST['txtName']) && isset($_POST['txtTel'])){
	$con=mysqli_connect("localhost","root","","girisimcimuhendis");
	$txtName = $_POST['txtName'];
	$txtTel = $_POST['txtTel'];
	$txtMajor = $_POST['txtMajor'];
	$txtBolum=$_POST['txtBolum'];
	$txtTelefon=$_POST['txtTelefon'];
	$txtBilgi=$_POST['txtBilgi'];
	$result=mysqli_query($con,"insert into uyekayit(adsoyad, ogrenci_numarasi, email,bolum,telefon_numarasi,bilgi) values('$txtName', '$txtTel', '$txtMajor','$txtBolum','$txtTelefon','$txtBilgi')");
	if($result){echo "Data Inserted";}
	else{echo "not Inserted";}
	
}
?>
<html>
<head>
<title>Insert Data</title>
</head>
<body>
<h1>Insert Data</h1>
	<form action="<?PHP $_PHP_SELF ?>" method="post">
		Student Name <input type="text" name="txtName" placeholder="Student Name" /> <br/> <br/>
		Tel <input type="text" name="txtTel" placeholder="Tel" /> <br/> <br/>
		Major <input type="text" name="txtMajor" placeholder="Major" /> <br/> <br/>
		Bolum <input type="text" name="txtBolum" placeholder="Bolum" /> <br/> <br/>
		Telefon <input type="text" name="txtTelefon" placeholder="Telefon" /> <br/> <br/>
		Bilgi <input type="text" name="txtBilgi" placeholder="Bilgi" /> <br/> <br/>
		<input type="submit" value="Insert" /> <br/>
	</form>
</body>
</html>	