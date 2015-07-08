<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select id_test, subject_test,teacher_test, level_test, session_test, date_test, duration_test, courses_test from test ORDER BY date_test desc");

  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>