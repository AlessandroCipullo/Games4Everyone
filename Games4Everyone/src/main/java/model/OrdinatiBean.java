package model;

public class OrdinatiBean {
	String cod_prodotto;
	String id_ordine;
	Double prezzo;
	Integer iva;
	Double sconto;
	Double prezzo_totale;
	Integer quantita;
	// nel caso in cui il prodotto viene eliminato è comunque possibile risalire al nome
	String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCod_prodotto() {
		return cod_prodotto;
	}
	public void setCod_prodotto(String cod_prodotto) {
		this.cod_prodotto = cod_prodotto;
	}
	public String getId_ordine() {
		return id_ordine;
	}
	public void setId_ordine(String id_ordine) {
		this.id_ordine = id_ordine;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	public Double getSconto() {
		return sconto;
	}
	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}
	public Double getPrezzo_totale() {
		return prezzo_totale;
	}
	public void setPrezzo_totale(Double prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
}
