<?php
  
  
   include '../connectionBd.php';

  $sql=mysql_query("select cin_etudiant, nom, prenom, inscription, genre, email, password, niveau, telephone, num_classe from etudiant where cin_etudiant = '".$_REQUEST['cin_etudiant']."'");

  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>