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
	public static ProdottoCarrello getProdotto(CarrelloBean cart, ProdottoBean prodotto) {
		int i = 0;
		
		for(i=0; i<cart.getProdotti().size(); i++) {
			if(cart.getProdotti().get(i).prod.getNome().equals(prodotto.getNome())) {
				return cart.getProdotti().get(i);
			}
		}
		return cart.getProdotti().get(0);
	}
	public static void removeProdotto(CarrelloBean cart, ProdottoBean prodotto) {
		int i = 0;
		
		for(i=0; i<cart.getProdotti().size(); i++) {
			if(cart.getProdotti().get(i).prod.getNome().equals(prodotto.getNome())) {
				cart.getProdotti().remove(i);
				return;
			}
		}
		return;
	}
	public static Boolean contiene(CarrelloBean cart,ProdottoBean prodotto) {
		return cart.getProdotti().stream().map(p -> p.prod.getNome()).filter(c -> c.equals(prodotto.nome)).findAny().isPresent();
	}
}
