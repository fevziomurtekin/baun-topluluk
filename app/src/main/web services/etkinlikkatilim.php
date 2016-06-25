<?PHP
if(isset($_POST['txtName']) && isset($_POST['txtTel'])){
	$con=mysqli_connect("localhost","root","","girisimcimuhendis");
	$txtName = $_POST['txtName'];
	$txtTel = $_POST['txtTel'];
	$txtBaslik=$_POST['txtBaslik'];
	$result=mysqli_query($con,"insert into katilim(adsoyad, ogrenci_numarasi,etkinlik_adi) values('$txtName', '$txtTel','$txtBaslik')");
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
		<input type="submit" value="Insert" /> <br/>
	</form>
</body>
</html>	