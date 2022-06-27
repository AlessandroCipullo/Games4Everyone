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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <link rel="stylesheet" href="./css/Home.css">
</head>
<body>
    <jsp:include page="Header.jsp"></jsp:include>
    <section class="prodotti">
      <div class="filtri">
        <a href="FiltriVendite?filtro=vendite&console=<%=console %>" style="text-decoration:none;"><p class="filtro1">I più venduti</p></a>
        <a href="FiltriVendite?filtro=data&console=<%=console %>" style="text-decoration:none;"><p class="filtro2">Nuove Uscite</p></a>
        <a href="FiltriVendite?filtro=preorder&console=<%=console %>" class="filtro2" style="text-decoration:none;">Prossime Uscite</a>
        <select class="filtro3">
        	<option value="" class="opt">Cerca per Genere</option>
        	<option value="avventura" class="opt">Avventura</option>
        	<option value="azione" class="opt">Azione</option>
        	<option value="arcade" class="opt">Arcade</option>
        	<option value="sparatutto" class="opt">Sparatutto</option>
        	<option value="simulazione" class="opt">Simulazione</option>
        	<option value="sopravvivenza" class="opt">Sopravvivenza</option>
        </select>
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
    
    <script>
	    $(document).ready(() => {
	    	$('select').on('change', function () {
	    		const val = this.value;	   
	    		window.location.href = "FiltriVendite?filtro=genere&console=<%=console %>&genere=" + val;
	    		});
		    })
    </script>
</body>
</html>
