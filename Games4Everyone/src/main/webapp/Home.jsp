<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.ProdottoDAO" import="model.ProdottoBean" import="java.util.*"%>
<%
	Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
	if(prodotti == null){
		prodotti = ProdottoDAO.retrieveAll();
	}
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
        <p class="filtro1">I pi&#249; venduti</p>
        <p class="filtro2">Nuove Offerte</p>
        <p class="filtro2">Nuove Uscite</p>
        <p class="filtro3">Top Preordini</p>
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
            <p class="prezzo"><%=bean.getPrezzo() %></p>
            <p class="sh"><%=bean.getSviluppatore() %></p>
          </div>
          <p class="acq"><a class="acq" href="DettaglioProdotto?id=<%=bean.getCod_prodotto() %>">Acquista ora</a></p>
        </div>
        <% }} %>
      </div>
    </section>
    
</body>
</html>
