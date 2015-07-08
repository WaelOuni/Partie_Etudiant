<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("INSERT INTO enseignant( cin_enseignant, nom, prenom,  genre, password, email, grade, specialite, telephone) VALUES ('".$_REQUEST['cin_enseignant']."','".$_REQUEST['nom']."','".$_REQUEST['prenom']."','".$_REQUEST['genre']."','".$_REQUEST['motpasse']."','".$_REQUEST['email']."','".$_REQUEST['grade']."','".$_REQUEST['specialite']."','".$_REQUEST['telephone']."')");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>