<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.ProdottoDAO" import="model.ProdottoBean" import="java.util.*"%>
<%
	Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
	if(prodotti == null){
		prodotti = ProdottoDAO.retrieveAll();
	}
	String console = (String) request.getAttribute("console");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Games 4 Everyone</title>
  <link rel="stylesheet" href="./css/Home.css">
</head>
<body>
    <jsp:include page="Header.jsp"></jsp:include>
    <section class="prodotti">
      <div class="filtri">
        <a href="FiltriVendite?filtro=vendite&console=<%=console %>" style="text-decoration:none;"><p class="filtro1">I più venduti</p></a>
        <a href="FiltriVendite?filtro=data&console=<%=console %>" style="text-decoration:none;"><p class="filtro2">Nuove Uscite</p></a>
        <p class="filtro2" style="text-decoration:none;">Nuove Offerte</p>
        <p class="filtro3" style="text-decoration:none;">Top Preordini</p>
      </div>
      <div class="schede">
      <%
			if (prodotti != null && prodotti.size() != 0) {
				Iterator<?> it = prodotti.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
        <div class="scheda">
          <div class="imgbox">
            <img class="img" src=<%=bean.getImgPath() %> alt="">
            <p class="prezzo">&euro;<%=bean.getPrezzo() %></p>
            <p class="sh"><%=bean.getSviluppatore() %></p>
          </div>
          <p class="acq"><a class="acq" href="DettaglioProdotto?id=<%=bean.getCod_prodotto() %>">Acquista ora</a></p>
        </div>
        <% }} %>
      </div>
    </section>
    
</body>
</html>
