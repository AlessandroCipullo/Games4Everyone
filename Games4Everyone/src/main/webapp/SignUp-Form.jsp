<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="./js/SignUp-Validation.js"></script>
    <title>SignUp</title>
</head>
<body>
	<h1>Registrazione</h1>
	<form method="post" action="SignUp" id="SignUpForm" onsubmit="return FormValidation()">
        <ul class="lista">
        <li>
           <label for="nome">Nome:</label>
           <input type="text" id="nome" name="nome" maxlength="20" required placeholder="Inserisci nome...">
         </li>
         <li>
           <label for="cognome">Cognome:</label>
           <input type="text" id="cognome" name="cognome" maxlength="25" required placeholder="Inserisci cognome...">
         </li>
         <li>
           <label for="cellulare">Cellulare:</label>
           <input type="text" id="cellulare" name="cellulare" maxlength="20" required placeholder="Inserisci cellulare...">
         </li>
         <li>
           <label for="username">Username:</label>
           <input type="text" id="username" name="username" maxlength="25" required placeholder="Inserisci username...">
         </li>
         <li>
           <label for="password">Password:</label>
           <input type="password" id="password" name="password" maxlength="25" required placeholder="Inserisci password..."><br>
           <input type="submit" id="submit" value="Registrati">
         </li>
        </ul>
       </form>
</body>
</html>