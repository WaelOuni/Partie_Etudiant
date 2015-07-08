<?php
  
 include '../connectionBd.php';
  $sql=mysql_query("select nom_course,  teacher_course, date_depo_course,  subject_test, teacher_test, level_test, date_test,id_test,level_test,session_test,date_test, duration_test, courses_test from course, test");
  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>