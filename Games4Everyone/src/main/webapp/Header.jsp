<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.UtenteBean"%>
<%
	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
	String usernameUtente = "";
	if(utente != null){
		usernameUtente = "Ciao " + utente.getUsername() + "!";		
	}
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/Header.css">
  <title>Games 4 Everyone</title>
</head>
<body>
	<section class="navbar">
      <img class="logo" src="./imgs/logo.png">
      <p class="saluto"><%=usernameUtente %></p>
      <form action="RicercaProdotti" method="get">
      	<input type="text" placeholder="Cerca un prodotto..." name="cerca" class="searchtxt">
      </form>
      <div class="icons">
        <img src="./imgs/login-icon.png">
        <a href="Login-Form.jsp" class="login">Accedi</a>
        <img src="./imgs/cart-icon.png">
        <p class="cart">Carrello</p>
      </div>
    </section>
    <section class="links">
      <a href="Home.jsp" class="link">Home</a>
      <img src="./imgs/home-icon.png">
      <a href="FiltroConsole?console=pc" class="link">PC</a>
      <img src="./imgs/pc-icon.png">
      <a href="FiltroConsole?console=Playstation" class="link">Playstation</a>
      <img src="./imgs/play-icon.png">
      <a href="FiltroConsole?console=XBox" class="link">XBox</a>
      <img src="./imgs/xbox-icon.png">
      <a href="FiltroConsole?console=NintendoSwitch" class="link">Nintendo</a>
      <img src="./imgs/switch-icon.png">
    </section>
</body>
</html>