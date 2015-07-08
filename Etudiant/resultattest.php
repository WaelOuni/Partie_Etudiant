<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select nom, prenom, numrepjust, numrepfalse, mention, rapidite, subject_test from resultattest, test, etudiant where cinEtudiant= cin_etudiant and idtest=id_test ");

   while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>
