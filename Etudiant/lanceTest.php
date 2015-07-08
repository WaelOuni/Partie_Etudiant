<?php
  
 include '../connectionBd.php';

  $sql=mysql_query("select numquestchoisis from test where id_test='".$_REQUEST['id']."'");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>