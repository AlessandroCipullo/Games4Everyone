package model;

import java.util.Date;

public class OrdineBean {
	String id_ordine;
	Date data;
	Double totale;
	String id_utente;
	String id_pag_utente;
	public String getId_ordine() {
		return id_ordine;
	}
	public void setId_ordine(String id_ordine) {
		this.id_ordine = id_ordine;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	public String getId_utente() {
		return id_utente;
	}
	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
	}
	public String getId_pag_utente() {
		return id_pag_utente;
	}
	public void setId_pag_utente(String id_pag_utente) {
		this.id_pag_utente = id_pag_utente;
	}
	
}
