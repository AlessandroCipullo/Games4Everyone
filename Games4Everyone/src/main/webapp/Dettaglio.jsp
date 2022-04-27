<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.ProdottoBean" import="model.ProdottoDAO"%>
    
<%
	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
	if(prodotto == null){
		response.sendRedirect("Home.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/Header.css">
<link rel="stylesheet" href="./css/Dettaglio.css">
<title><%=prodotto.getNome() %> - Acquista</title>
</head>
<body bgcolor="#1a0e0c">
	<jsp:include page="Header.jsp"></jsp:include>
	<section class="prodotto">
		<p class="nome"><%=prodotto.getNome() %>
		<img class="img" src="<%=prodotto.getImgPath()%>">
		<p class="descrizione">Descrizione:<br><%=prodotto.getDescrizione() %></p> 
		<p class="prezzo">Prezzo: &euro;<%=prodotto.getPrezzo() %></p>
		<!-- Firefox blocca la visualizzazione di un video esterno (Youtube) tramite link -->
		<iframe class="trailer" src="<%=prodotto.getTrailer() %>?autoplay=1&mute=1"></iframe>
	</section>
</body>
</html>