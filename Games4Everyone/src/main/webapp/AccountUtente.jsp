<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.UtenteBean" import="java.util.*" import="model.PagamentoBean"%>
<%
	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
	if(utente == null){
		response.sendRedirect("./Login-Form.jsp");
	    return;
	}

	Collection<?> metodi = (Collection<?>) request.getAttribute("metodi");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Games 4 Everyone</title>
  <link rel="stylesheet" href="./css/AccountUtente.css">
  <script src="./js/AccountUtente.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<section class="top">
	  	<img class="logo" src="./imgs/logo.png">
	</section>
	<p class="welcome">Ciao <%=utente.getUsername() %>!</p>
	<button id="accordion0">I miei metodi di pagamento</button>
		<button id="panel1">Aggiungi nuovo metodo di pagamento</button>
		<a href="MetodiPagamento?action=null"><button id="panel2">Visualizza i miei metodi di pagamento</button></a>
	<button id="accordion1">I miei dati</button>
	<a href="GestioneOrdini?action=v"><button id="accordion1">I miei ordini</button></a>
	
	<form method="post" action="MetodiPagamento" id="addForm">
		<p class="inserisciTag">Inserisci i dati:</p>
		<input type="hidden" name="action" value="add"> 
		<label for="tipo" class="tipolbl">Tipo:</label><br>
        <input type="text" id="tipo" maxlength="25" name="tipo" required placeholder="Inserisci il tipo..."><br>
        <label for="provider" class="providerlbl">Provider:</label><br>
        <input type="text" id="provider" maxlength="25" name="provider" required placeholder="Inserisci provider..."><br>
		<label for="codice" class="codlbl">Codice:</label><br>
        <input type="text" id="codice" maxlength="25" name="codice" required placeholder="Inserisci il codice..."><br>
        <label for="scadenza" class="scadenzalbl" >Scadenza:</label><br>
        <input type="date" id="scadenza" name="scadenza" required><br>
        <input type="submit" id="submit" value="Aggiungi">
	</form>
	
	<% if(metodi != null){ %>
	
	<table border="1" id="tabellaMetodi">
		<tr>
			<th>Tipo</th>
			<th>Provider</th>
			<th>Scadenza</th>
			<th>Codice</th>
			<th>Ammontare</th>
		</tr>
		<%
			if (metodi != null && metodi.size() != 0) {
				Iterator<?> it = metodi.iterator();
				while (it.hasNext()) {
					PagamentoBean bean = (PagamentoBean) it.next();
		%>
		<tr>
			<td><%=bean.getTipo()%></td>
			<td><%=bean.getProvider()%></td>
			<td><%=bean.getScadenza().toString()%></td>
			<td><%=bean.getCodice()%></td>
			<td><%=bean.getAmmontare()%></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non sono presenti metodi di pagamento</td>
		</tr>
		<%
			}}
		%>
	</table>
	<a href="Home.jsp"><center><h2>Torna alla home</h2></center></a>
</body>
</html>