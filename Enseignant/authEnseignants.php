<?php
   include '../connectionBd.php';
  $sql=mysql_query("select email, password from enseignant");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>