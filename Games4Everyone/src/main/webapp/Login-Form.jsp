<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
      <form method="post" action="Login" id="LoginForm">
        <ul class="lista">
         <li>
           <label for="username">Username:</label>
           <input type="text" id="username" maxlength="25" name="username" required placeholder="Inserisci username...">
         </li>
         <li>
           <label for="password">Password:</label>
           <input type="password" id="password" maxlength="20" name="password" required placeholder="Inserisci password..."><br>
           <input type="submit" id="submit" value="Accedi">
         </li>
        </ul>
       </form>
       <a href="SignUp-Form.jsp"><input type="button" value="Registrati"></a>
</body>
</html>