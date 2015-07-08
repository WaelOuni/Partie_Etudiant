<?php
  
mysql_connect("localhost","root","");

  mysql_select_db("myproject");

  $sql=mysql_query("INSERT INTO questiontext(enonce, reponse, matiere, niveau) VALUES ('".$_REQUEST['enonce']."','".$_REQUEST['reponse']."','".$_REQUEST['matiere']."','".$_REQUEST['niveau']."')");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>