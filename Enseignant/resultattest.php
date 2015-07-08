<?php
  
  include '../connectionBd.php';


  $sql=mysql_query("select id_test from test where seance = '".$_REQUEST['seance']."' AND date = '".$_REQUEST['date']."' AND matiere= '".$_REQUEST['matiere']."'");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>