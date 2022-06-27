<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.UtenteBean" import="java.util.*" import="model.PagamentoBean" import="model.CarrelloDAO"%>
 <%
	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
	if(utente == null){
		response.sendRedirect("./Login-Form.jsp");
	    return;
	}

	Collection<?> metodi = (Collection<?>) request.getAttribute("metodi");
	if(metodi == null){
		response.sendRedirect("./MetodiPagamento?action=choose");
	}
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Games 4 Everyone</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<table border="1" id="tabellaMetodi">
		<tr>
			<th>Provider</th>
			<th>Codice</th>
			<th>Ammontare</th>
			<th>Scegli</th>
		</tr>
		<%
			if (metodi != null && metodi.size() != 0) {
				Iterator<?> it = metodi.iterator();
				while (it.hasNext()) {
					PagamentoBean bean = (PagamentoBean) it.next();
		%>
		<tr>
			<td><%=bean.getProvider()%></td>
			<td><%=bean.getCodice()%></td>
			<td><%=bean.getAmmontare()%></td>
			<td><a href="Acquisto?action=a&codice=<%=bean.getCodice() %>" onclick="return Conferma();">Scegli</a><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non sono presenti metodi di pagamento</td>
		</tr>
		<%
			}
		%>
		</table>
		<a href="Home.jsp"><h2>Torna alla home</h2></a>
		
		<script>
			function Conferma(){
				let scelta = confirm("Importo totale: <%=CarrelloDAO.totCarrello(utente.getId_utente()) %>\n\nConfermi l'acquisto?")
				if(scelta){
					return true;
				}
					return false;
			}
		</script>
</body>
</html>