<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select id_subject, name_subject, teacher_subject from subject ");
  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>