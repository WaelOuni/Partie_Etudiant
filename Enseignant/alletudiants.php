<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select cin_etudiant, nom, prenom, inscription, genre, email, password, niveau, telephone from etudiant");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>