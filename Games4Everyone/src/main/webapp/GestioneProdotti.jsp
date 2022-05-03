<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.ProdottoDAO" import="model.ProdottoBean" import="java.util.*"%>

<%
	Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
	if ((adminRoles == null) || (!adminRoles.booleanValue()))
	{	
	    response.sendRedirect("./Login-Form.jsp");
	    return;
	}
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./ControlloProdotti");	
		return;
	}
	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
%>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestione Prodotti</title>
</head>

<body>
	<h2>Prodotti</h2>
	<table border="1">
		<tr>
			<th>Cod</th>
			<th>Nome</th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<tr>
			<td><%=bean.getCod_prodotto()%></td>
			<td><%=bean.getNome()%></td>
			<td><a href="ControlloProdotti?action=delete&cod=<%=bean.getCod_prodotto()%>">Delete</a><br>
				<a href="ControlloProdotti?action=read&cod=<%=bean.getCod_prodotto()%>">Details</a></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non sono presenti prodotti</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<h2>Dettagli</h2>
	<%
		if (product != null) {
	%>
	<table border="1">
		<tr>
			<th>Cod</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Prezzo</th>
			<th>Iva</th>
			<th>Quantita</th>
			<th>Rilascio</th>
			<th>Piattaforma</th>
			<th>Sviluppatore</th>
			<!-- Inserisci genere -->
			<th>Trailer</th>
			<th>ImgPath</th>
		</tr>
		<tr>
			<td><%=product.getCod_prodotto()%></td>
			<td><%=product.getNome()%></td>
			<td><%=product.getDescrizione()%></td>
			<td><%=product.getPrezzo()%></td>
			<td><%=product.getIva() %></td>
			<td><%=product.getQuantità()%></td>
			<td><%=product.getData_rilascio() %></td>
			<td><%=product.getPiattaforma() %></td>
			<td><%=product.getSviluppatore() %></td>
			<!-- Inserisci genere -->
			<td><%=product.getTrailer() %></td>
			<td><%=product.getImgPath() %></td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Inserisci prodotto</h2>
	<form action="ControlloProdotti" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="nome">Nome:</label><br> 
		<input name="nome" type="text" maxlength="45" required><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="600" rows="3" required></textarea><br>
		
		<label for="prezzo">Prezzo:</label><br> 
		<input name="prezzo" type="number" min="0" value="0" required><br>
		
		<label for="iva">Iva:</label><br>
		<input name="iva" type="number" min="0" value="0" required><br>

		<label for="quantita">Quantità:</label><br> 
		<input name="quantita" type="number" min="1" value="1" required><br>
		
		<label for="rilascio">Data di rilascio:</label><br>
		<input name="rilascio" type="date" required><br>
		
		<!-- Genere da inserire -->
		
		<label for="piattaforma">Piattaforma:</label><br>
		<input name="piattaforma" type="radio" id="play" value="Playstation">
		<label for="play">Playstation</label><br>
		<input name="piattaforma" type="radio" id="pc" value="PC">
		<label for="pc">PC</label><br>
		<input name="piattaforma" type="radio" id="xbox" value="XBox">
		<label for="xbox">XBox</label><br>
		<input name="piattaforma" type="radio" id="nintendo" value="NintendoSwitch">
		<label for="nintendo">Nintendo</label><br>
		
		<label for="dev">Sviluppatore:</label><br>
		<input name="dev" type="text" maxlength="45" required placeholder="Inserisci sviluppatore"><br>
		
		<label for="trailer">Url del trailer:</label><br>
		<input name="trailer" type="text" required placeholder="Inserisci trailer"><br> <!-- Link esterni accettati -->
		
		<label for="imgpath">Url dell'immagine:</label><br>
		<input name="imgpath" type="text" required placeholder="Inserisci immagine"><br> <!-- Link esterni accettati -->

		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">

	</form>
</body>
</html>