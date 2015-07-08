<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select id_subject, name_subject from subject ");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>