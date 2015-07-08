<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select enonce, reponse from questiontext where  idquest= '".$_REQUEST['id']."'");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>