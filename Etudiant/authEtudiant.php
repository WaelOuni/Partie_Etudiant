<?php
  
  
   include '../connectionBd.php';

  $sql=mysql_query("select cin_etudiant, email, password from etudiant");

  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>