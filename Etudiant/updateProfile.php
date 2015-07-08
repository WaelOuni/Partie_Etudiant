<?php
 include '../connectionBd.php';
  $sql=mysql_query("UPDATE etudiant SET email= '".$_REQUEST['email']."' , password= '".$_REQUEST['password']."' , niveau= '".$_REQUEST['niveau']."' , telephone= '".$_REQUEST['telephone']."' , num_classe= '".$_REQUEST['num_classe']."' WHERE cin_etudiant = '".$_REQUEST['cin_etudiant']."'");

  
  mysql_close();
?>
