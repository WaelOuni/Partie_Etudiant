<?php
  
  include '../connectionBd.php';

  $sql=mysql_query("select id, enonce, reponse, matiere, niveau from questioncasecocher");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>