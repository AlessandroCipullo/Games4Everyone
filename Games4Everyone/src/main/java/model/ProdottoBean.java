package model;

import java.sql.Date;

public class ProdottoBean {
	String cod_prodotto;
	String nome;
	String genere;
	String sottogenere;
	String piattaforma;
	String descrizione;
	String trailer;
	String imgPath;
	Integer sconto;
	String sviluppatore;
	Double prezzo;
	Integer iva;
	Date data_rilascio;
	public String getSottogenere() {
		return sottogenere;
	}
	public void setSottogenere(String sottogenere) {
		this.sottogenere = sottogenere;
	}
	public Integer getVendite() {
		return vendite;
	}
	public void setVendite(Integer vendite) {
		this.vendite = vendite;
	}
	Integer quantità;
	Integer vendite;
	public String getSviluppatore() {
		return sviluppatore;
	}
	public void setSviluppatore(String sviluppatore) {
		this.sviluppatore = sviluppatore;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getPiattaforma() {
		return piattaforma;
	}
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getSconto() {
		return sconto;
	}
	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}
	public String getCod_prodotto() {
		return cod_prodotto;
	}
	public void setCod_prodotto(String cod_prodotto) {
		this.cod_prodotto = cod_prodotto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Date getData_rilascio() {
		return data_rilascio;
	}
	public void setData_rilascio(Date date) {
		this.data_rilascio = date;
	}
	public Integer getQuantità() {
		return quantità;
	}
	public void setQuantità(Integer quantità) {
		this.quantità = quantità;
	}
}
