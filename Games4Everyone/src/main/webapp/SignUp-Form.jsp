<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
<title>Registrazione</title>
</head>
<body>
	<h1>Registrazione</h1>
	<form method="post" action="SignUp" id="form">
        <ul class="lista">
        <li>
           <label for="nome">Nome:</label>
           <input type="text" id="nome" name="nome">
         </li>
         <li>
           <label for="cognome">Cognome:</label>
           <input type="text" id="cognome" name="cognome">
         </li>
         <li>
           <label for="cellulare">Cellulare:</label>
           <input type="text" id="cellulare" name="cellulare">
         </li>
         <li>
           <label for="username">Username:</label>
           <input type="text" id="username" name="username">
         </li>
         <li>
           <label for="password">Password:</label>
           <input type="password" id="password" name="password"><br>
           <input type="submit" id="submit" value="Registrati">
         </li>
        </ul>
       </form>
</body>
</html>