<?php 

 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','girisimcimuhendis');
 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $username = $_POST['username'];
 $password = $_POST['password'];
 
 //Creating sql query
 $sql ="SELECT * from uyekayit where adsoyad='$username' and ogrenci_numarasi ='$password'";
 

 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }
 ?>
 <html>
    <head>
        <title>Register</title>

    </head>
    <body>
        <h1>Insert Data</h1>
        <form action="" method="post">
		Username 
            <input type="text" name="username" placeholder="username" />
            <br/>
            <br/>
		Password 
            <input type="text" name="password" placeholder="password" />
            <br/>
            <br/>
		
            <input type="submit" value="Insert" />
            <br/>
	
        </form>

    </body>

</html>