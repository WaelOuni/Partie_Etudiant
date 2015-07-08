<?php
  
 include '../connectionBd.php';


  $sql=mysql_query("INSERT INTO questioncasecache statement, choice1, choice2, choice3, answer,id_subject, cin_teacher) VALUES ('".$_REQUEST['enoncecc']."','".$_REQUEST['choixa']."','".$_REQUEST['choixb']."','".$_REQUEST['choixc']."','".$_REQUEST['reponsecc']."','".$_REQUEST['matierecc']."','".$_REQUEST['niveaucc']."')");

  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;

  print(json_encode($output));

  mysql_close();
?>