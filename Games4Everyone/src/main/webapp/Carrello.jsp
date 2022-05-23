<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.CarrelloBean" import="model.CarrelloDAO" import="java.util.*" import="model.CarrelloBean.ProdottoCarrello"%>
<% 
	CarrelloBean cart = (CarrelloBean) request.getAttribute("cart");
	if(cart == null){
		cart = (CarrelloBean) request.getSession().getAttribute("cart");
		if(cart == null){
			response.sendRedirect("Home.jsp");
		}
	}
	Double tot = CarrelloDAO.totCarrello(cart.getId_carrello());
%>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/Carrello.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <title>Carrello</title>
</head>

<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<div class="carrello">
		
		<div class="totale">
			<p>Totale provvisorio</p><br>
			<p id="tot"><%=tot %></p>
			<a href="Acquisto?id=<%=cart.getId_carrello() %>">Procedi all'acquisto</a>
		</div>
		
        <p class="carrellotag">Il tuo Carrello</p>
        <div class="prodotti">
        
	<%
		Iterator<ProdottoCarrello> it = cart.getProdotti().iterator();
		while(it.hasNext()){
			ProdottoCarrello pc = it.next();
	%>
	
			<div class="scheda">
                <hr class="linea">
                <a href="DettaglioProdotto?id=<%=pc.prod.getCod_prodotto() %>"><img src="<%=pc.prod.getImgPath() %>" alt="" class="prodimg"></a>
                <p class="nome"><%=pc.prod.getNome() %></p>
                <p class="descrizione"><%=pc.prod.getDescrizione() %></p>
         		<input type="number" name="sel" value="<%=pc.quantita %>" min="1" max="10" class="quantita" 
         			oninput="modificaSel(this.value, <%=cart.getId_carrello() %>, <%=pc.prod.getCod_prodotto() %>)">     			
                <a href="CarrelloOp?id=<%=cart.getId_carrello() %>&cod=<%=pc.prod.getCod_prodotto() %>&action=r"><img src="./imgs/bin-mini-icon.png" alt="" class="binIcon"></a>
            </div>
            
            <script>    
			function modificaSel(sel, id, cod){
				const xhttp = new XMLHttpRequest();
				xhttp.open("GET", "CarrelloOp?id="+ id +"&cod="+ cod + "&sel="+sel+"&action=s");
				xhttp.send();
			}
			
			$(document).ready(() => {
			      $('.quantita').on('input', () => {
			    	  const xhttp = new XMLHttpRequest();
			    		xhttp.onload = function() {
			    		    $("#tot").html(this.responseText);
			    		  };
			    		  xhttp.open("GET", "TotaleCarrello?id=<%=cart.getId_carrello() %>");
			    		  xhttp.send();
			        });
			    })
			</script>
	<% } %>
		</div>

	</div>
</body>
</html>
