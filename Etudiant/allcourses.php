<?php
  
 include '../connectionBd.php';
  $sql=mysql_query("select id_course, nom_course, description_course, url_course, teacher_course, date_depo_course, name_subject  from course, subject where id_subject=id_subject_course ORDER BY date_depo_course desc");
  while($row=mysql_fetch_array($sql))
  $output[]=$row;

  $tab= array('auth' => $output);
  print(json_encode($tab));

  mysql_close();

?>