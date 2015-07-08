<?php
 include '../connectionBd.php';

  $sql=mysql_query("select cin_etudiant, nom, prenom from etudiant where email= '".$_REQUEST['email']."'");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>