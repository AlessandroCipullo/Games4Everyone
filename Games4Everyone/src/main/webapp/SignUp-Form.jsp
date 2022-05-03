<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="./js/SignUp-Validation.js"></script>
    <link rel="stylesheet" href="./css/SignUp.css">
    <title>SignUp</title>
</head>
<body bgcolor="#F45F5F">
	<section class="top">
	  	<img class="logo" src="./imgs/logo.png">
	</section>
	<section class="form">
		<form method="post" action="SignUp" id="SignUpForm" onsubmit="return FormValidation()">
		   <p class="registrati">Registrazione</p>
           <label for="nome" class="nomelbl">Nome:</label>
           <input type="text" id="nome" name="nome" maxlength="20" required placeholder="Inserisci nome...">

           <label for="cognome" class="cognomelbl">Cognome:</label>
           <input type="text" id="cognome" name="cognome" maxlength="25" required placeholder="Inserisci cognome...">

           <label for="cellulare" class="cellbl">Cellulare:</label>
           <input type="text" id="cellulare" name="cellulare" maxlength="20" required placeholder="Inserisci cellulare...">

           <label for="username" class="usrlbl">Username:</label>
           <input type="text" id="username" name="username" maxlength="25" required placeholder="Inserisci username...">

           <label for="password" class="pwdlbl">Password:</label>
           <input type="password" id="password" name="password" maxlength="25" required placeholder="Inserisci password..."><br>
           <input type="submit" id="submit" value="Registrati">
       </form>
	</section>
	<div class="login">
	  	<p class="accedi">Hai già un account?</p>
      	<a href="Login-Form.jsp"><input type="button" class="loginbtn" value="Accedi"></a>	  	
	  </div>
</body>
</html>