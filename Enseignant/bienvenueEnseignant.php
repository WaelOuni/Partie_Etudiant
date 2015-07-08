<?php
  
 include '../connectionBd.php';


  $sql=mysql_query("select nom, prenom from enseignant where email= '".$_REQUEST['email']."'");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>