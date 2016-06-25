<?php
if(isset($_POST['username']) && isset($_POST['password'])){
	$con=mysqli_connect("localhost","root","","umutbankasi");
	mysqli_connect("localhost","root","","umutbankasi")or die("Baglanamadim");
	$username = $_POST['username'];
	$password = $_POST['password'];
	$sorgu=mysqli_query($con,"SELECT * from kullanicilar WHERE username=$username and password=$password");
	if($sorgu){
		$rowcount=mysqli_num_rows($sorgu);
		if($rowcount>0){echo "$username bulundu";}
		else if($rowcount<0){echo "bulunamadi";}
		mysqli_free_result($result);
	}
	else{echo"if e giremedi.";}
	mysqli_close($con);


?>
<html>
<head>
<title>Register</title>
</head>
<body>
<h1>Insert Data</h1>
	<form action="<?PHP $_PHP_SELF ?>" method="post">
		Username <input type="text" name="username" placeholder="Username" /> <br/> <br/>
		Password <input type="text" name="password" placeholder="Password" /> <br/> <br/>
		<input type="submit" value="Insert" /> <br/>
	</form>
</body>
</html>