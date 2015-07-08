<?php
 include '../connectionBd.php';

 
 $sql=mysql_query("select e.cin_etudiant, nom, prenom, numrepjust,  mention, rapidite from etudiant e, resultattest r where idtest='".$_REQUEST['id']."' AND e.cin_etudiant=cinEtudiant");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>
