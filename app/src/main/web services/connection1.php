<?PHP
require_once 'config1.php';
class DB_Connection{
	private $connect;
	function _construct(){
		$this->connect=mysqli_connect(hostname, user, password, db_name)or die("DB connection error");}
		public function get_connection(){return $this->connect;}
	
}

?>