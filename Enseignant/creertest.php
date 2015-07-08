<?php
 include '../connectionBd.php';


  $sql=mysql_query("INSERT INTO test(subject_test,level_test,session_test,date_test,duration_test,duration_test, numquestchoisis) VALUES ('".$_REQUEST['matiere']."','".$_REQUEST['niveau']."','".$_REQUEST['seance']."','".$_REQUEST['date']."','".$_REQUEST['duree']."','".$_REQUEST['coursprepare']."','".$_REQUEST['numquestchoisis']."')");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>

