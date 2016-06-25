<?php
include_once 'connection1.php';
	
	class User {
		
		private $db;
		private $connection;
		private $con;
		function __construct() {
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
			$this->con=mysqli_connect("localhost","root","","girisimcimuhendis") or die("baglanamadi");
		
		}
		
		public function does_user_exist($username,$password)
		{	$con=mysqli_connect("localhost","root","","girisimcimuhendis");
			$query = "SELECT * from uyekayit where adsoyad='$username' and ogrenci_numarasi ='$password'";
			$result = mysqli_query($con, $query);
			if(mysqli_num_rows($result)>0){
				$json['success'] = 1;
				echo json_encode($json);
				mysqli_close($con);
			}else{
				$json['error']=0;
				echo json_encode($json);
				mysqli_close($con);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['username'],$_POST['password'])) {
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		if(!empty($username) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user->does_user_exist($username,$password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
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