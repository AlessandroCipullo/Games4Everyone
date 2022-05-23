<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/Login.css">
    <title>Login</title>
</head>
<body bgcolor="#F45F5F">
	  <section class="top">
	  	<img class="logo" src="./imgs/logo.png">
	  </section>
	  <section class="form">
	  	<form method="post" action="Login" id="LoginForm">
	  	   <p class="accedi">Login</p>
           <label for="username" class="usrlbl">Username:</label><br>
           <input type="text" id="username" maxlength="25" name="username" onload="focus(this)" required placeholder="Inserisci username..."><br>
           <label for="password" class="pwdlbl">Password:</label>
           <input type="password" id="password" maxlength="20" name="password" required placeholder="Inserisci password..."><br>
           <input type="submit" id="submit" value="Accedi">
           <input type="checkbox" id="check" name="check" value="checked">
           <label for="check" class="lblcheck">Ricordami</label>
       </form>
	  </section>
	  <div class="signup">
	  	<p class="registrati">Sei nuovo su Games4Everyone?</p>
      	<a href="SignUp-Form.jsp"><input type="button" class="signupbtn" value="Registrati"></a>	  	
	  </div>
      <script>
      	function focus(this){
      		$(this).on("mouseover", () => {
      			this.focus();
      		})
      	}
      </script>
</body>
</html>