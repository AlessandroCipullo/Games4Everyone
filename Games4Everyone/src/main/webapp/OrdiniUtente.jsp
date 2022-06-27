<%@page import="model.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*" import="java.util.*"%>
<html lang="en">
<%
	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
	if(utente == null){
		response.sendRedirect("./Login-Form.jsp");
	    return;
	}
	
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
	Collection<?> ordinati = (Collection<?>) request.getAttribute("ordinati");

%>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/AccountUtente.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <title>I miei Ordini</title>
</head>
<body>
	<section class="top">
	  	<img class="logo" src="./imgs/logo.png">
	</section>
	<p class="welcome">Ciao <%=utente.getUsername() %>!</p>
	
	<% 
		if(ordini != null){
	%>
	<table border="1" id="tabellaOrdini">
		<tr>
			<th>Data</th>
			<th>Totale</th>
			<th>Codice</th>
			<th></th>
		</tr>
		<%
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		<tr>
			<td><%=bean.getData()%></td>
			<td><%=bean.getTotale()%></td>
			<td><%=PagamentoDAO.retrieveById(bean.getId_pag_utente()).getCodice() %>
			<td><a href="GestioneOrdini?action=p&ordineid=<%=bean.getId_ordine() %>">Visualizza</a></td>
		</tr>
		<%
				}} else {
		%>
		<tr>
			<td colspan="6">Non sono presenti ordini</td>
		</tr>
		<%
			}} else {
		%>
		<table border="1" id="tabellaOrdinati">
			<tr>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th>Iva</th>
				<th>Quantita</th>
			</tr>
			<%
				if (ordinati != null && ordinati.size() != 0) {
					Iterator<?> it = ordinati.iterator();
					while (it.hasNext()) {
						OrdinatiBean bean = (OrdinatiBean) it.next();
			%>
			<tr>
			<td><a href="DettaglioProdotto?id=<%=bean.getCod_prodotto() %>"><%=bean.getNome()%></a></td>
			<td><%=bean.getPrezzo()%></td>
			<td><%=bean.getIva()%>
			<td><%=bean.getQuantita() %>
		</tr>
		<% }}} %>
	</table>
	<a href="Home.jsp"><h2>Torna alla home</h2></a>
</body>
</html>