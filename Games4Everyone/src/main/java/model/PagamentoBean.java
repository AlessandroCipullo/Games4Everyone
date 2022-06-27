package model;

import java.util.Date;

public class PagamentoBean {
	String id_pag_utente;
	String id_utente;
	String codice;
	String tipo;
	String provider;
	Date scadenza;
	Double ammontare;
	
	public String getId_pag_utente() {
		return id_pag_utente;
	}
	public void setId_pag_utente(String id_pag_utente) {
		this.id_pag_utente = id_pag_utente;
	}
	public String getId_utente() {
		return id_utente;
	}
	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	public Double getAmmontare() {
		return ammontare;
	}
	public void setAmmontare(Double ammontare) {
		this.ammontare = ammontare;
	}
	
}
