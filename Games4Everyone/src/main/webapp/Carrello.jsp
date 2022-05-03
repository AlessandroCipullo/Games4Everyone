<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.CarrelloBean" import="model.CarrelloDAO" import="java.util.*" import="model.CarrelloBean.ProdottoCarrello"%>
<% 
	CarrelloBean cart = (CarrelloBean) request.getAttribute("cart");
	if(cart == null){
		response.sendRedirect("Home.jsp");
	}
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/Carrello.css">
  <title>Carrello</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="carrello">
        <p class="carrellotag">Il tuo Carrello</p>
        <div class="prodotti">
	<%
		Iterator<ProdottoCarrello> it = cart.getProdotti().iterator();
		while(it.hasNext()){
			ProdottoCarrello pc = it.next();
	%>
			<div class="scheda">
                <hr class="linea">
                <img src="<%=pc.prod.getImgPath() %>" alt="" class="prodimg">
                <p class="descrizione"><%=pc.prod.getNome() %></p>
                <input type="number" value="<%=pc.quantita %>" min="1" max="10" class="quantita">
            </div>
	<% } %>
		</div>
	</div>
</body>
</html>
