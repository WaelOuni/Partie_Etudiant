<?php
 include '../connectionBd.php';
  $sql=mysql_query("INSERT INTO etudiant VALUES ('".$_REQUEST['cin_etudiant']."','".$_REQUEST['nom']."','".$_REQUEST['prenom']."','".$_REQUEST['inscription']."','".$_REQUEST['genre']."','".$_REQUEST['email']."','".$_REQUEST['password']."','".$_REQUEST['niveau']."','".$_REQUEST['telephone']."','".$_REQUEST['num_classe']."')");

  mysql_close();
?>

										