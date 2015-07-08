<?php
  
 include '../connectionBd.php';


  $sql=mysql_query("select id_test, subject_test, teacher_test, level_test, session_test, date_test , duration_test from test");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>