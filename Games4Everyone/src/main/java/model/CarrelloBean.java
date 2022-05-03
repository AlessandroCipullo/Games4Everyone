package model;

import java.util.ArrayList;

public class CarrelloBean {	
	public static class ProdottoCarrello {
		public ProdottoBean prod;
		public Integer quantita;
		public ProdottoCarrello(ProdottoBean prod, Integer quantita) {
			this.prod = prod;
			this.quantita = quantita;
		}
	}
	
	String id_carrello;
	ArrayList<ProdottoCarrello> prodotti = new ArrayList<ProdottoCarrello>();
	
	public String getId_carrello() {
		return id_carrello;
	}
	public void setId_carrello(String id_carrello) {
		this.id_carrello = id_carrello;
	}
	public ArrayList<ProdottoCarrello> getProdotti() {
		return prodotti;
	}
	public void setProdotti(ProdottoBean prodotto, Integer quant) {
		prodotti.add(new ProdottoCarrello(prodotto, quant));
	}
}
