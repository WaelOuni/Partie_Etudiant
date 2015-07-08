<?php
  
  include '../connectionBd.php';

  $sql=mysql_query("select numrepjust from resultattest  where idtest='".$_REQUEST['id']."'");

	
  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>