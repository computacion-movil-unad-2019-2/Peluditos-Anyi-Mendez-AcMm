<?PHP
$hostname="localhost";
$database="peluditos";
$username="apps";
$password="123456789";
$json=array();
	if(isset($_GET["usuario"]) && isset($_GET["pwd"])){
		$user=$_GET['usuario'];
		$pwd=$_GET['pwd'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database)
		or die ("No se ha podido conectar al servidor de Base de datos");
		
		$consulta="SELECT user, pwd, nombres FROM usuarios WHERE user= '{$usuario}' AND pwd = '{$pwd}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}



		else{
			$results["user"]='';
			$results["pwd"]='';
			$results["nombres"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
		   	$results["user"]='';
			$results["pwd"]='';
			$results["nombres"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}

		
?>