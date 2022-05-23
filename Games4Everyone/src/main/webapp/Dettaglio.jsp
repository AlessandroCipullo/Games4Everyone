<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.ProdottoBean" import="model.ProdottoDAO"%>
    
<%
	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
	if(prodotto == null){
		response.sendRedirect("Home.jsp");
		return;
	}
	String idUtente = (String) request.getSession().getAttribute("idUtente");
	if(idUtente != null){
		if(idUtente.equals("null")){
			idUtente = "";
		}
		request.getSession().removeAttribute("idUtente");
	}
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/Header.css">
<link rel="stylesheet" href="./css/Dettaglio.css">
<link rel="icon" type="image/x-icon" href="<%=prodotto.getImgPath() %>">
<title><%=prodotto.getNome() %> - Acquista</title>
</head>
<body bgcolor="#1a0e0c">
	<jsp:include page="Header.jsp"></jsp:include>
	<section class="prodotto">
		<p class="nome"><%=prodotto.getNome() %>
		<img class="img" src="<%=prodotto.getImgPath()%>">
		<p class="descrizione">Descrizione:<br><%=prodotto.getDescrizione() %></p> 
		<p class="prezzo">Prezzo: &euro;<%=prodotto.getPrezzo() %></p>
		<img src="./imgs/cart-mini-icon.png" alt="" class="cartIcon">
		<a href="CarrelloOp?id=<%=idUtente %>&cod=<%=prodotto.getCod_prodotto() %>&action=a" class="addToCart" >Aggiungi al carrello</a>
		<!-- Firefox blocca la visualizzazione di un video esterno (Youtube) tramite link -->
		<iframe class="trailer" src="<%=prodotto.getTrailer() %>?autoplay=1&mute=1"></iframe>
	</section>
</body>
</html>