<?php
  
  include '../connectionBd.php';
  $sql=mysql_query("insert into resultattest VALUES ('".$_REQUEST['cin']."','".$_REQUEST['numrepjust']."','".$_REQUEST['mention']."','".$_REQUEST['rapidite']."','".$_REQUEST['idtest']."')"); 
  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>